package kr.co.apfactory.storesolution.global.file.util;

import kr.co.apfactory.storesolution.global.file.domain.dto.DownloadFileDTO;
import kr.co.apfactory.storesolution.global.file.domain.dto.UploadResultDTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

@Component
public class FileManager {
    public UploadResultDTO uploadFile(MultipartFile mf, String uploadPath) throws Exception {
        // 기본 업로드 폴더 경로 존재하지 않으면 생성
        if (!new File(uploadPath).exists()) {
            new File(uploadPath).mkdirs();
        }

        // 파일 원래이름 가져오기
        String originalName = mf.getOriginalFilename();

        // 랜덤 접두어 + 원래 파일 이름 = 저장할 파일이름
        String savedFileName = getUuidFileName(originalName);

        // 경로 생성
        String subPath = calcPath(uploadPath);

        String fullPath = uploadPath + subPath;

        File target = new File(fullPath, savedFileName);

        // 서버로 파일 복사
        FileCopyUtils.copy(mf.getBytes(), target);

        UploadResultDTO uploadResultDTO = UploadResultDTO.builder()
                .originalFileName(originalName) // 원래 파일명
                .fileName(savedFileName)   // 저장 파일명
                .folderPath(subPath) // 저장 경로
                .build();

        return uploadResultDTO;
    }

    public File makeFile(String uploadedPathFile) {
        return new File(uploadedPathFile);
    }

    /**
     * 파일명 중복 방지를 위해 UUID 파일명 생성 * * @param filename * @return
     */
    public String getUuidFileName(String filename) {
        UUID uuid = UUID.randomUUID();
        StringBuilder sb = new StringBuilder();
        sb.append(uuid).append("_").append(FilenameUtils.getBaseName(filename)).append(".").append(FilenameUtils.getExtension(filename));
        return sb.toString();
    }

    // 서버의 파일 삭제
    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() == true) {
            file.delete();
        }
    }

    public String calcPath(String uploadPath) {
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

    public void downloadFile(HttpServletResponse response, DownloadFileDTO dto) {

        try {
            byte[] data = FileUtils.readFileToByteArray(makeFile(dto.getUploadedPathFile()));
            if (dto.isImage()) {
                response.setContentType(MimeMedia.getMediaType(FilenameUtils.getExtension(dto.getFilename())).toString());
            }
            response.setContentLength(data.length);
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(dto.getFilename(), "UTF-8") + "\";");
            response.getOutputStream().write(data);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException("다운로드에 실패하였습니다.");
        } catch (Exception e) {
            throw new RuntimeException("시스템에 문제가 발생하였습니다.");
        }
    }
}
