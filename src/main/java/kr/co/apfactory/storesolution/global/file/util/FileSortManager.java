package kr.co.apfactory.storesolution.global.file.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileSortManager {
    public List<MultipartFile> sortFilesByName(List<MultipartFile> files) {
        return files.stream()
                .sorted(Comparator.comparing(
                        MultipartFile::getOriginalFilename, String.CASE_INSENSITIVE_ORDER
                ))
                .collect(Collectors.toList());
    }
}
