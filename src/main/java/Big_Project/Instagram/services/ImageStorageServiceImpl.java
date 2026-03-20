package Big_Project.Instagram.services;

import Big_Project.Instagram.configs.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageStorageServiceImpl implements ImageStorageService {

    private final MinioClient minioClient;
    private final MinioProperties props;

    @Override
    public String uploadImage(MultipartFile file) {

        try {

            boolean bucketExists = minioClient.bucketExists(
                    io.minio.BucketExistsArgs.builder()
                            .bucket(props.getBucket())
                            .build()
            );

            if (!bucketExists) {

                minioClient.makeBucket(
                        io.minio.MakeBucketArgs.builder()
                                .bucket(props.getBucket())
                                .build()
                );

            }

            String fileName =
                    UUID.randomUUID() + "-" + file.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(props.getBucket())
                            .object(fileName)
                            .stream(
                                    file.getInputStream(),
                                    file.getSize(),
                                    -1
                            )
                            .contentType(file.getContentType())
                            .build()
            );

            return props.getUrl()
                    + "/"
                    + props.getBucket()
                    + "/"
                    + fileName;

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Image upload failed", e);

        }
    }
}
