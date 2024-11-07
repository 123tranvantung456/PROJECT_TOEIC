package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.ChapterConverter;
import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.model.response.*;
import com.javaweb.toeic.repository.ChapterRepository;
import com.javaweb.toeic.repository.LessonRepository;
import com.javaweb.toeic.repository.VocabularyListInCourseRepository;
import com.javaweb.toeic.service.ChapterService;
import com.javaweb.toeic.service.LessonService;
import com.javaweb.toeic.service.UserService;
import com.javaweb.toeic.service.VocabularyListInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private VocabularyListInCourseRepository vocabularyListInCourseRepository;
    @Autowired
    private ChapterConverter chapterConverter;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private VocabularyListInCourseService vocabularyListInCourseService;

    @Override
    public ChapterInMyCourseResponseDTO getChapterById(Long chapterId) {
        ChapterEntity chapterEntity = chapterRepository.findById(chapterId).get();
        return tochapterInMyCourseResponseDTO(chapterEntity);
    }

    @Override
    public List<ChapterInMyCourseResponseDTO> getChapterInCourse(Long courseId) {
        List<ChapterEntity> chapterEntities = chapterRepository.findByCourseIdOrderByOrderNumber(courseId);
        List<ChapterInMyCourseResponseDTO> result = new ArrayList<>();
        for (ChapterEntity chapterEntity : chapterEntities) {
            result.add(tochapterInMyCourseResponseDTO(chapterEntity));
        }
        return result;
    }

    @Override
    public List<ContentOfChapterResponseDTO> getContentInChapter(Long chapterId) {
        List<LessonEntity> lessonEntities = lessonRepository.findByChapter_Id(chapterId);
        List<VocabularyListInCourseEntity> vocabularyListInCourseEntities =
                vocabularyListInCourseRepository.findByChapter_Id(chapterId);
        List<Object> contentOfChapters = new ArrayList<>();
        contentOfChapters.addAll(vocabularyListInCourseEntities);
        contentOfChapters.addAll(lessonEntities);
        sortContentOfChapters(contentOfChapters);
        List<ContentOfChapterResponseDTO> result = new ArrayList<>();
        for (Object contentOfChapter : contentOfChapters) {
            ContentOfChapterResponseDTO contentOfChapterResponseDTO = chapterConverter.
                    toContentOfChapterResponseDTO(contentOfChapter);
            if (contentOfChapterResponseDTO instanceof LessonInMyChapterResponseDTO lessonInMyChapterResponseDTO) {
                lessonInMyChapterResponseDTO.setContentOfLessonResponseDTOs
                        (lessonService.getContentOfLesson(lessonInMyChapterResponseDTO.getId()));
                result.add(lessonInMyChapterResponseDTO);
            } else if (contentOfChapterResponseDTO instanceof VocabularyListInChapterResponseDTO
                    vocabularyListInChapterResponseDTO) {
                vocabularyListInChapterResponseDTO.setHasVocabulary
                        (vocabularyListInCourseService.hasVocabulary(vocabularyListInChapterResponseDTO.getId()));
                result.add(vocabularyListInChapterResponseDTO);
            }
        }
        return result;
    }

    @Override
    public List<ChapterResponseDTO> getChapter(Long chapterId) {
        Long courseId = chapterRepository.findById(chapterId).get().getCourse().getId();
        List<ChapterEntity> chapterEntities = chapterRepository.findByCourseId(courseId);
        List<ChapterResponseDTO> result = new ArrayList<>();
        for (ChapterEntity chapterEntity : chapterEntities) {
            ChapterResponseDTO chapterResponseDTO = chapterConverter.toChapterResponseDTO(chapterEntity);
            result.add(chapterResponseDTO);
        }
        return result;
    }

    @Override
    public ContentNextResponseDTO getContentNextOfChapter(Long chapterId, Long lessonId) {
        Integer orderNumberCurrent = lessonRepository.findById(lessonId).get().getOrderNumber();
        LessonEntity lessonNext = lessonRepository.
                findFirstByOrderNumberGreaterThanAndChapter_IdOrderByOrderNumberAsc
                        (orderNumberCurrent, chapterId).orElseGet(() -> {
                    LessonEntity defaultLesson = new LessonEntity();
                    defaultLesson.setOrderNumber(0);
                    return defaultLesson;
                });
        VocabularyListInCourseEntity vocabNext = vocabularyListInCourseRepository.
                findFirstByOrderNumberGreaterThanAndChapter_IdOrderByOrderNumberAsc
                        (orderNumberCurrent, chapterId).orElseGet(() -> {
                    VocabularyListInCourseEntity defaultVocabularyListInCourse = new VocabularyListInCourseEntity();
                    defaultVocabularyListInCourse.setOrderNumber(0);
            return defaultVocabularyListInCourse;
        });
        ContentNextResponseDTO contentOfLessonNextResponseDTO = null;
        if(lessonNext.getOrderNumber() == 0 && vocabNext.getOrderNumber() == 0){
            return null;
        }
        else if ((vocabNext.getOrderNumber() > 0 && lessonNext.getOrderNumber() > 0 &&
                vocabNext.getOrderNumber() < lessonNext.getOrderNumber()) || lessonNext.getOrderNumber() == 0) {
            contentOfLessonNextResponseDTO = new ContentNextResponseDTO();
            contentOfLessonNextResponseDTO.setContentOfChapterName("danh sach tu vung");
        }
        else {
            contentOfLessonNextResponseDTO = lessonService.getFirstContentOfLesson(lessonNext.getId());
            contentOfLessonNextResponseDTO.setContentOfChapterName(lessonNext.getName());
            contentOfLessonNextResponseDTO.setContentOfChapterId(lessonNext.getId());
        }

        return contentOfLessonNextResponseDTO;
    }

    private void sortContentOfChapters(List<Object> contentOfChapters) {
        contentOfChapters.sort(Comparator.comparingInt(o -> {
            if (o instanceof VocabularyListInCourseEntity) {
                return ((VocabularyListInCourseEntity) o).getOrderNumber();
            } else if (o instanceof LessonEntity) {
                return ((LessonEntity) o).getOrderNumber();
            }
            return Integer.MAX_VALUE;
        }));
    }

    private int countContentOfLessonInChapter(ChapterEntity chapterEntity) {
        int result = 0;
        for (LessonEntity lesson : chapterEntity.getLessons()) {
            result += lesson.getLessonContents().size();
            result += lesson.getExerciseFormatGrammars().size();
            result += lesson.getExerciseFormatTOIECs().size();
        }
        result += chapterEntity.getVocabularyListInCourses().size();
        return result;
    }

    private ChapterInMyCourseResponseDTO tochapterInMyCourseResponseDTO(ChapterEntity chapterEntity) {
        ChapterInMyCourseResponseDTO chapterInMyCourseResponseDTO =
                chapterConverter.tochapterInMyCourseResponseDTO(chapterEntity);
        int completeRate = 0;
        int countContentOfLessonInChapter = countContentOfLessonInChapter(chapterEntity);
        if (countContentOfLessonInChapter == 0) {
            completeRate = -1;
        } else {
            completeRate = (int) Math.round(1.0 * userService.countCompleteContentOfLessonByUserInChapter
                    (chapterEntity.getId()) / countContentOfLessonInChapter * 100);
        }
        chapterInMyCourseResponseDTO.setCompleteRate(completeRate);
        return chapterInMyCourseResponseDTO;
    }
}
