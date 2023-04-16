package cn.hellp.lingsmc.safeop.utils;

import cn.hellp.lingsmc.safeop.SafeOp;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Level;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/16
 */
public class ConfigUtils {
    private static final String SERVER_PATH = System.getProperty("user.dir");
    private static final SafeOp PLUGIN = SafeOp.getInstance();
    private static final String BACKUP_PATH = SERVER_PATH + "/plugins/SafeOp/ops.json";
    private static final String OPS_PATH = SERVER_PATH + "/ops.json";

    private ConfigUtils() {
    }

    public static void initialize() {
        // Check if a backup exists.
        if (!checkBackup()) {
            return;
        }
        // 初始化配置文件
        File itemsFolder = new File(SERVER_PATH + "/plugins/SafeOp");
        if (!itemsFolder.exists()) {
            try{
                Files.createDirectory(itemsFolder.toPath());
            } catch (IOException e){
                PLUGIN.getLogger().log(Level.SEVERE, "创建备份文件夹失败，插件将无法正常工作。",e);
            }
        }
        File opsFile = new File(OPS_PATH);
        File backup = new File(BACKUP_PATH);
        // Backup opsFile.
        try{
            copy(opsFile, backup);
        } catch (IOException e){
            PLUGIN.getLogger().log(Level.SEVERE, "备份OP列表失败，插件将无法正常工作。",e);
        }
    }

    /**
     * Check if a backup exists.
     *
     * @return false if a backup exists.
     */
    public static boolean checkBackup() {
        File backup = new File(BACKUP_PATH);
        if (backup.exists() && !diff(backup, new File(OPS_PATH))) {
            PLUGIN.getLogger().log(Level.WARNING, "检测到服务器未正常关闭且OP列表出错，正在恢复OP列表...");
            try {
                copy(backup, new File(OPS_PATH));
                PLUGIN.getLogger().log(Level.FINE, "OP列表已恢复！重启中...");
            } catch (IOException e) {
                PLUGIN.getLogger().log(Level.SEVERE, "恢复OP列表失败！",e);
            }
            Bukkit.shutdown();
            return false;
        }
        return true;
    }

    /**
     * Copy file1 to file2.
     *
     * @param file1 source file
     * @param file2 target file
     */
    public static void copy(File file1, File file2) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (final InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(file1.toPath()), StandardCharsets.UTF_8);
             final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(file2.toPath()), StandardCharsets.UTF_8)) {
            int len;
            char[] chars = new char[1 << 14];
            while ((len = inputStreamReader.read(chars)) != -1) {
                builder.append(new String(chars, 0, len));
            }
            outputStreamWriter.write(builder.toString());
        }
    }

    /**
     * Compare two files.
     * @param file1 first file
     * @param file2 second file
     * @return true if two files are the same.
     */
    public static boolean diff(File file1,File file2){
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        try (final InputStreamReader inputStreamReader1 = new InputStreamReader(Files.newInputStream(file1.toPath()), StandardCharsets.UTF_8);
             final InputStreamReader inputStreamReader2 = new InputStreamReader(Files.newInputStream(file2.toPath()), StandardCharsets.UTF_8)) {
            int len;
            char[] chars = new char[1 << 14];
            while ((len = inputStreamReader1.read(chars)) != -1) {
                builder1.append(new String(chars, 0, len));
            }
            while ((len = inputStreamReader2.read(chars)) != -1) {
                builder2.append(new String(chars, 0, len));
            }
            return builder1.toString().equals(builder2.toString());
        } catch (IOException e) {
            PLUGIN.getLogger().log(Level.SEVERE, "对比OP列表失败，插件将无法正常工作。",e);
            return false;
        }
    }

    /**
     * Remove backup.
     */
    public static void removeBackup() {
        try{
            Files.delete(new File(BACKUP_PATH).toPath());
        } catch (IOException e) {
            PLUGIN.getLogger().log(Level.SEVERE, "删除OP列表备份失败，插件将无法正常工作。",e);
        }
    }
}
