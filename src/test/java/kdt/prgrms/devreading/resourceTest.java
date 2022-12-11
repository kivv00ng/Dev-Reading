package kdt.prgrms.devreading;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class resourceTest {

    Logger logger = LoggerFactory.getLogger(resourceTest.class);

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void test() throws IOException {
        Resource resource = resourceLoader.getResource("");

        File file = new File(resource.getFile().getCanonicalPath() + "/efefe.txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File created");
                logger.info("###" + file.getAbsolutePath());
            } else {
                System.out.println("File already exists");
                logger.info("###" + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
