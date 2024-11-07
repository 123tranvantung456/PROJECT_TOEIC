package com.javaweb.toeic.service;

import org.springframework.core.io.Resource;
import java.io.IOException;

public interface WordProcessingService {
    String readWordFile(Resource resource) throws IOException;
}
