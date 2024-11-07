package com.javaweb.toeic.service.impl;

// Import các thư viện cần thiết
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.javaweb.toeic.config.StorageProperties;
import com.javaweb.toeic.exception.StorageException;
import com.javaweb.toeic.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// Đánh dấu class này là một service trong Spring
@Service
public class FileSystemStorageServiceImpl implements StorageService {

    // Đường dẫn gốc để lưu trữ file
    private final Path rootLocation;

    // Hàm tạo cho class, sử dụng StorageProperties để khởi tạo rootLocation
    public FileSystemStorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    // Hàm này dùng để tạo tên file lưu trữ bằng cách sử dụng extension của file gốc và một id cho trước
    @Override
    public String getStorageFilename(MultipartFile file, String id) {
        // Lấy phần mở rộng của tên file gốc
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        // Tạo tên file mới với định dạng "p<id>.<extension>"
        return "p" + id + "." + ext;
    }

    @Override
    public void store(byte[] data, String storeFilename) {
        try {
            // Xác định đường dẫn đích
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(storeFilename))
                    .normalize().toAbsolutePath();

            // Đảm bảo rằng file được lưu trong thư mục gốc
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Không thể lưu trữ file bên ngoài thư mục hiện tại");
            }

            // Lưu trữ dữ liệu byte array vào đường dẫn đích
            Files.write(destinationFile, data);
        } catch (Exception e) {
            throw new StorageException("Không thể lưu trữ file: " + e.getMessage(), e);
        }
    }


    // Hàm này dùng để lưu trữ file vào vị trí chỉ định với tên file đã cho
    @Override
    public void store(MultipartFile file, String storeFilename) {
        try {
            // Kiểm tra nếu file rỗng
            if (file.isEmpty()) {
                throw new StorageException("Không thể lưu trữ file rỗng");
            }
            // Xác định đường dẫn đích đến nơi file sẽ được lưu trữ
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(storeFilename))
                    .normalize().toAbsolutePath();

            // Đảm bảo rằng file được lưu trong thư mục gốc
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Không thể lưu trữ file bên ngoài thư mục hiện tại");
            }

            // Sao chép file vào đường dẫn đích
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new StorageException("Không thể lưu trữ file: " + e.getMessage(), e);
        }
    }

    // Hàm này dùng để tải một file như một tài nguyên dựa trên tên file
    @Override
    public Resource loadAsResource(String filename) {
        try {
            // Tải đường dẫn file dựa trên tên file
            Path file = load(filename);
            // Tạo một đối tượng Resource cho URI của file
            Resource resource = new UrlResource(file.toUri());

            // Kiểm tra xem resource có tồn tại và có thể đọc được hay không
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException("Không thể đọc file: " + filename);
            }
        } catch (Exception e) {
            throw new StorageException("Không thể đọc file: " + filename, e);
        }
    }

    // Hàm này dùng để tải đường dẫn của file dựa trên tên file
    @Override
    public Path load(String filename) {
        // Xác định đường dẫn file trong thư mục gốc
        return rootLocation.resolve(filename);
    }

    // Hàm này dùng để xóa file dựa trên tên file lưu trữ
    @Override
    public void delete(String storeFilename) throws Exception {
        // Xác định đường dẫn đích đến của file cần xóa
        Path destinationFile = rootLocation.resolve(Paths.get(storeFilename))
                .normalize().toAbsolutePath();
        // Xóa file tại đường dẫn đã xác định
        Files.delete(destinationFile);
    }

    // Hàm này khởi tạo dịch vụ lưu trữ bằng cách tạo các thư mục cần thiết
    @Override
    public void init() {
        try {
            // Tạo thư mục tại vị trí rootLocation nếu chưa tồn tại
            Files.createDirectories(rootLocation);
            // In ra đường dẫn rootLocation
            System.out.println(rootLocation.toString());
        } catch (Exception e) {
            throw new StorageException("Không thể khởi tạo lưu trữ: ", e);
        }
    }
}
