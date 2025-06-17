package kr.co.apfactory.storesolution.global.file.application.service;

import kr.co.apfactory.storesolution.global.file.domain.repository.FileAttachMasterRepository;
import kr.co.apfactory.storesolution.global.file.domain.repository.FileAttachRepository;
import kr.co.apfactory.storesolution.global.file.util.FileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class FileService {
    private final Environment environment;

    private final FileManager fileManager;

    private final FileAttachRepository fileAttachRepository;

    private final FileAttachMasterRepository fileAttachMasterRepository;

    private String calcPath(String uploadPath) {
        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator + cal.get(Calendar.YEAR);

        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath.replaceAll("\\\\", "/");
    }

    private void makeDir(String uploadPath, String... paths) {
        if (new File(paths[paths.length - 1]).exists()) {
            return;
        }

        for (String path : paths) {
            File dirPath = new File(uploadPath + path);

            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }
}
