package io.github.gefangshuai.wfinal.base.utils;

import com.jfinal.upload.UploadFile;

import java.io.File;

/**
 * Jfinal 框架扩展的零散工具类
 * Created by gefangshuai on 2015/7/20.
 */
public final class JfinalUTKit {

    /**
     * 文件上传并重命名
     *
     * @param uploadFile
     * @return
     */
    public static File uploadAndRenameFile(UploadFile uploadFile) {
        return uploadAndRenameFile(uploadFile.getSaveDirectory(), uploadFile);
    }

    /**
     * 文件上传并重命名
     *
     * @param saveDir
     * @param uploadFile
     * @return
     */
    public static File uploadAndRenameFile(String saveDir, UploadFile uploadFile) {
        File dir = new File(saveDir);
        if (!dir.exists())
            dir.mkdirs();
        File desFile = new File(dir.getAbsolutePath() + "/" + System.nanoTime() + uploadFile.getFileName().substring(uploadFile.getFileName().lastIndexOf(".")));
        uploadFile.getFile().renameTo(desFile);
        return desFile;
    }
}
