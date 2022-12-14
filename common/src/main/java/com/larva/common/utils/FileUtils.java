package com.larva.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-24 11:40
 * @describe:
 **/
public class FileUtils {
    public static final String DOT = ".";
    public static final String SLASH_ONE = "/";
    public static final String SLASH_TWO = "\\";
    private static final Pattern schemePrefixPattern = Pattern.compile("(file:*[a-z]:)|(\\w+://.+?/)|((jar|zip):.+!/)|(\\w+:)", Pattern.CASE_INSENSITIVE);
    /**
     * 获取没有扩展名的文件名
     *
     * @param fileName
     * @return
     */
    public static String getWithoutExtension(String fileName) {
        String ext = org.apache.commons.lang3.StringUtils.substring(fileName, 0,
                org.apache.commons.lang3.StringUtils.lastIndexOf(fileName, DOT));
        return trimToEmpty(ext);
    }

    /**
     * 获取扩展名
     *
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        if (org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND == org.apache.commons.lang3.StringUtils.indexOf(fileName, DOT))
            return org.apache.commons.lang3.StringUtils.EMPTY;
        String ext = org.apache.commons.lang3.StringUtils.substring(fileName,
                org.apache.commons.lang3.StringUtils.lastIndexOf(fileName, DOT));
        return trimToEmpty(ext);
    }

    /**
     * 获取文件名称
     *
     * @param file
     * @return
     */
    public static String getFilename(String file) {
        String[] seps = getWithoutExtension(file).split(File.separator);
        return trimToEmpty(seps[seps.length - 1]);
    }

    /**
     * 判断是否同为扩展名
     *
     * @param fileName
     * @param ext
     * @return
     */
    public static boolean isExtension(String fileName, String ext) {
        return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(getExtension(fileName), ext);
    }

    /**
     * 判断是否存在扩展名
     *
     * @param fileName
     * @return
     */
    public static boolean hasExtension(String fileName) {
        return !isExtension(fileName, org.apache.commons.lang3.StringUtils.EMPTY);
    }

    /**
     * 得到正确的扩展名
     *
     * @param ext
     * @return
     */
    public static String trimExtension(String ext) {
        return getExtension(DOT + ext);
    }

    /**
     * 向path中填充扩展名(如果没有或不同的话)
     *
     * @param fileName
     * @param ext
     * @return
     */
    public static String fillExtension(String fileName, String ext) {
        fileName = replacePath(fileName + DOT);
        ext = trimExtension(ext);
        if (!hasExtension(fileName)) {
            return fileName + getExtension(ext);
        }
        if (!isExtension(fileName, ext)) {
            return getWithoutExtension(fileName) + getExtension(ext);
        }
        return fileName;
    }

    /**
     * 判断是否是文件PATH
     *
     * @param fileName
     * @return
     */
    public static boolean isFile(String fileName) {
        return hasExtension(fileName);
    }

    /**
     * 判断是否是文件夹PATH
     *
     * @param fileName
     * @return
     */
    public static boolean isFolder(String fileName) {
        return !hasExtension(fileName);
    }

    public static String replacePath(String path) {
        return org.apache.commons.lang3.StringUtils.replace(trimToEmpty(path), SLASH_ONE,
                SLASH_TWO);
    }

    /**
     * 链接PATH前处理
     *
     * @param path
     * @return
     */
    public static String trimLeftPath(String path) {
        if (isFile(path))
            return path;
        path = replacePath(path);
        String top = org.apache.commons.lang3.StringUtils.left(path, 1);
        if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(SLASH_TWO, top))
            return org.apache.commons.lang3.StringUtils.substring(path, 1);
        return path;
    }

    /**
     * 链接PATH后处理
     *
     * @param path
     * @return
     */
    public static String trimRightPath(String path) {
        if (isFile(path))
            return path;
        path = replacePath(path);
        String bottom = org.apache.commons.lang3.StringUtils.right(path, 1);
        if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(SLASH_TWO, bottom))
            return org.apache.commons.lang3.StringUtils.substring(path, 0, path.length() - 2);
        return path + SLASH_TWO;
    }

    /**
     * 链接PATH前后处理，得到准确的链接PATH
     *
     * @param path
     * @return
     */
    public static String trimPath(String path) {
        path = org.apache.commons.lang3.StringUtils.replace(trimToEmpty(path), SLASH_ONE,
                SLASH_TWO);
        path = trimLeftPath(path);
        path = trimRightPath(path);
        return path;
    }

    /**
     * 通过数组完整链接PATH
     *
     * @param paths
     * @return
     */
    public static String bulidFullPath(String... paths) {
        StringBuffer sb = new StringBuffer();
        for (String path : paths) {
            sb.append(trimPath(path));
        }
        return sb.toString();
    }
    /**
     * 从指定文件，指定编码读取文件
     *
     * @param file     待读入的文件
     * @param encoding 读入编码
     */
    public static String readFileContent(File file, String encoding) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        return readStreamContent(fis, encoding);
    }

    /**
     * 从指定输入流，指定编码读取文件
     *
     * @param stream   待读入的输入流
     * @param encoding 读入编码
     */
    public static String readStreamContent(InputStream stream, String encoding) throws Exception {
        StringBuilder content = new StringBuilder("");
        byte[] bytearray = new byte[stream.available()];
        int bytetotal = stream.available();
        while (stream.read(bytearray, 0, bytetotal) != -1) {
            String temp = new String(bytearray, 0, bytetotal, encoding);
            content.append(temp);
        }
        return content.toString();
    }

    /**
     * 输出代码到指定目录
     *
     * @param file          要输出的文件
     * @param outputContext 输出内容
     */
    public static void writeFileContent(File file, String outputContext) throws IOException {
        boolean b = !file.exists() ? file.createNewFile() : file.delete();

        FileOutputStream fop = new FileOutputStream(file);
        fop.write(outputContext.getBytes());
        fop.flush();
        fop.close();
    }

    /**
     * 规格化绝对路径。
     * <p>
     * 该方法返回以“<code>/</code>”开始的绝对路径。转换规则如下：
     * </p>
     * <ol>
     * <li>路径为空，则返回<code>""</code>。</li>
     * <li>将所有backslash("\\")转化成slash("/")。</li>
     * <li>去除重复的"/"或"\\"。</li>
     * <li>去除"."，如果发现".."，则向上朔一级目录。</li>
     * <li>保留路径末尾的"/"（如果有的话，除了空路径）。</li>
     * <li>对于绝对路径，如果".."上朔的路径超过了根目录，则看作非法路径，抛出异常。</li>
     * </ol>
     * <p>
     * param path 要规格化的路径
     * return 规格化后的路径
     * throws IllegalPathException 如果路径非法
     */
    public static String normalizeAbsolutePath(String path) throws Exception {
        return normalizePath(path, true, false, false);
    }

    /**
     * 规格化绝对路径。
     * <p>
     * 该方法返回以“<code>/</code>”开始的绝对路径。转换规则如下：
     * </p>
     * <ol>
     * <li>路径为空，则返回<code>""</code>。</li>
     * <li>将所有backslash("\\")转化成slash("/")。</li>
     * <li>去除重复的"/"或"\\"。</li>
     * <li>去除"."，如果发现".."，则向上朔一级目录。</li>
     * <li>保留路径末尾的"/"（如果有的话，除了空路径和强制指定<code>removeTrailingSlash==true</code>）。</li>
     * <li>对于绝对路径，如果".."上朔的路径超过了根目录，则看作非法路径，抛出异常。</li>
     * </ol>
     * <p>
     * param path                要规格化的路径
     * param removeTrailingSlash 是否强制移除末尾的<code>"/"</code>
     * return 规格化后的路径
     * throws IllegalPathException 如果路径非法
     */
    public static String normalizeAbsolutePath(String path, boolean removeTrailingSlash) throws Exception {
        return normalizePath(path, true, false, removeTrailingSlash);
    }

    /**
     * 规格化相对路径。
     * <p>
     * 该方法返回不以“<code>/</code>”开始的相对路径。转换规则如下：
     * </p>
     * <ol>
     * <li>路径为空，则返回<code>""</code>。</li>
     * <li>将所有backslash("\\")转化成slash("/")。</li>
     * <li>去除重复的"/"或"\\"。</li>
     * <li>去除"."，如果发现".."，则向上朔一级目录。</li>
     * <li>空相对路径返回""。</li>
     * <li>保留路径末尾的"/"（如果有的话，除了空路径）。</li>
     * </ol>
     * <p>
     * param path 要规格化的路径
     * return 规格化后的路径
     * throws IllegalPathException 如果路径非法
     */
    public static String normalizeRelativePath(String path) throws Exception {
        return normalizePath(path, false, true, false);
    }

    /**
     * 规格化相对路径。
     * <p>
     * 该方法返回不以“<code>/</code>”开始的相对路径。转换规则如下：
     * </p>
     * <ol>
     * <li>路径为空，则返回<code>""</code>。</li>
     * <li>将所有backslash("\\")转化成slash("/")。</li>
     * <li>去除重复的"/"或"\\"。</li>
     * <li>去除"."，如果发现".."，则向上朔一级目录。</li>
     * <li>空相对路径返回""。</li>
     * <li>保留路径末尾的"/"（如果有的话，除了空路径和强制指定<code>removeTrailingSlash==true</code>）。</li>
     * </ol>
     * <p>
     * param path                要规格化的路径
     * param removeTrailingSlash 是否强制移除末尾的<code>"/"</code>
     * return 规格化后的路径
     * throws IllegalPathException 如果路径非法
     */
    public static String normalizeRelativePath(String path, boolean removeTrailingSlash) throws Exception {
        return normalizePath(path, false, true, removeTrailingSlash);
    }

    /**
     * 规格化路径。规则如下：
     * <ol>
     * <li>路径为空，则返回<code>""</code>。</li>
     * <li>将所有backslash("\\")转化成slash("/")。</li>
     * <li>去除重复的"/"或"\\"。</li>
     * <li>去除"."，如果发现".."，则向上朔一级目录。</li>
     * <li>空绝对路径返回"/"，空相对路径返回""。</li>
     * <li>保留路径末尾的"/"（如果有的话，除了空路径）。</li>
     * <li>对于绝对路径，如果".."上朔的路径超过了根目录，则看作非法路径，抛出异常。</li>
     * </ol>
     * <p>
     * param path 要规格化的路径
     * return 规格化后的路径
     * throws IllegalPathException 如果路径非法
     */
    public static String normalizePath(String path) throws Exception {
        return normalizePath(path, false, false, false);
    }

    /**
     * 规格化路径。规则如下：
     * <ol>
     * <li>路径为空，则返回<code>""</code>。</li>
     * <li>将所有backslash("\\")转化成slash("/")。</li>
     * <li>去除重复的"/"或"\\"。</li>
     * <li>去除"."，如果发现".."，则向上朔一级目录。</li>
     * <li>空绝对路径返回"/"，空相对路径返回""。</li>
     * <li>保留路径末尾的"/"（如果有的话，除了空路径和强制指定<code>removeTrailingSlash==true</code>）。</li>
     * <li>对于绝对路径，如果".."上朔的路径超过了根目录，则看作非法路径，抛出异常。</li>
     * </ol>
     * <p>
     * param path                要规格化的路径
     * param removeTrailingSlash 是否强制移除末尾的<code>"/"</code>
     * return 规格化后的路径
     * throws IllegalPathException 如果路径非法
     */
    public static String normalizePath(String path, boolean removeTrailingSlash) throws Exception {
        return normalizePath(path, false, false, removeTrailingSlash);
    }

    private static String normalizePath(String path, boolean forceAbsolute, boolean forceRelative, boolean removeTrailingSlash) throws Exception {
        char[] pathChars = trimToEmpty(path).toCharArray();
        int length = pathChars.length;

        // 检查绝对路径，以及path尾部的"/"
        boolean startsWithSlash = false;
        boolean endsWithSlash = false;

        if (length > 0) {
            char firstChar = pathChars[0];
            char lastChar = pathChars[length - 1];

            startsWithSlash = firstChar == '/' || firstChar == '\\';
            endsWithSlash = lastChar == '/' || lastChar == '\\';
        }

        StringBuilder buf = new StringBuilder(length);
        boolean isAbsolutePath = forceAbsolute || !forceRelative && startsWithSlash;
        int index = startsWithSlash ? 0 : -1;
        int level = 0;

        if (isAbsolutePath) {
            buf.append("/");
        }

        while (index < length) {
            // 跳到第一个非slash字符，或末尾
            index = indexOfSlash(pathChars, index + 1, false);

            if (index == length) {
                break;
            }

            // 取得下一个slash index，或末尾
            int nextSlashIndex = indexOfSlash(pathChars, index, true);

            String element = new String(pathChars, index, nextSlashIndex - index);
            index = nextSlashIndex;

            // 忽略"."
            if (".".equals(element)) {
                continue;
            }

            // 回朔".."
            if ("..".equals(element)) {
                if (level == 0) {
                    // 如果是绝对路径，../试图越过最上层目录，这是不可能的，
                    // 抛出路径非法的异常。
                    if (isAbsolutePath) {
                        throw new Exception(path);
                    } else {
                        buf.append("../");
                    }
                } else {
                    buf.setLength(pathChars[--level]);
                }

                continue;
            }

            // 添加到path
            pathChars[level++] = (char) buf.length(); // 将已经读过的chars空间用于记录指定level的index
            buf.append(element).append('/');
        }

        // 除去最后的"/"
        if (buf.length() > 0) {
            if (!endsWithSlash || removeTrailingSlash) {
                buf.setLength(buf.length() - 1);
            }
        }

        return buf.toString();
    }

    private static int indexOfSlash(char[] chars, int beginIndex, boolean slash) {
        int i = beginIndex;

        for (; i < chars.length; i++) {
            char ch = chars[i];

            if (slash) {
                if (ch == '/' || ch == '\\') {
                    break; // if a slash
                }
            } else {
                if (ch != '/' && ch != '\\') {
                    break; // if not a slash
                }
            }
        }

        return i;
    }

    // ==========================================================================
    // 取得基于指定basedir规格化路径。
    // ==========================================================================

    /**
     * 如果指定路径已经是绝对路径，则规格化后直接返回之，否则取得基于指定basedir的规格化路径。
     * <p>
     * param basedir 根目录，如果<code>path</code>为相对路径，表示基于此目录
     * param path    要检查的路径
     * return 规格化的绝对路径
     * throws IllegalPathException 如果路径非法
     */
    public static String getAbsolutePathBasedOn(String basedir, String path) throws Exception {
        // 如果path为绝对路径，则规格化后返回
        boolean isAbsolutePath = false;

        path = trimToEmpty(path);

        if (path.length() > 0) {
            char firstChar = path.charAt(0);
            isAbsolutePath = firstChar == '/' || firstChar == '\\';
        }

        if (!isAbsolutePath) {
            // 如果path为相对路径，将它和basedir合并。
            if (path.length() > 0) {
                path = trimToEmpty(basedir) + "/" + path;
            } else {
                path = trimToEmpty(basedir);
            }
        }

        return normalizeAbsolutePath(path);
    }

    /**
     * 取得和系统相关的绝对路径。
     * <p>
     * throws IllegalPathException 如果basedir不是绝对路径
     */
    public static String getSystemDependentAbsolutePathBasedOn(String basedir, String path) throws Exception {
        path = trimToEmpty(path);

        boolean endsWithSlash = path.endsWith("/") || path.endsWith("\\");

        File pathFile = new File(path);

        if (pathFile.isAbsolute()) {
            // 如果path已经是绝对路径了，则直接返回之。
            path = pathFile.getAbsolutePath();
        } else {
            // 否则以basedir为基本路径。
            // 下面确保basedir本身为绝对路径。
            basedir = trimToEmpty(basedir);

            File baseFile = new File(basedir);

            if (baseFile.isAbsolute()) {
                path = new File(baseFile, path).getAbsolutePath();
            } else {
                throw new Exception("Basedir is not absolute path: " + basedir);
            }
        }

        if (endsWithSlash) {
            path = path + '/';
        }

        return normalizePath(path);
    }

    // ==========================================================================
    // 取得相对于指定basedir相对路径。
    // ==========================================================================

    /**
     * 取得相对于指定根目录的相对路径。
     * <p>
     * param basedir 根目录
     * param path    要计算的路径
     * return 如果<code>path</code>和<code>basedir</code>是兼容的，则返回相对于
     * <code>basedir</code>的相对路径，否则返回<code>path</code>本身。
     * throws IllegalPathException 如果路径非法
     */
    public static String getRelativePath(String basedir, String path) throws Exception {
        // 取得规格化的basedir，确保其为绝对路径
        basedir = normalizeAbsolutePath(basedir);

        // 取得规格化的path
        path = getAbsolutePathBasedOn(basedir, path);

        // 保留path尾部的"/"
        boolean endsWithSlash = path.endsWith("/");

        // 按"/"分隔basedir和path
        String[] baseParts = StringUtils.split(basedir, '/');
        String[] parts = StringUtils.split(path, '/');
        StringBuilder buf = new StringBuilder();
        int i = 0;

        while (i < baseParts.length && i < parts.length && baseParts[i].equals(parts[i])) {
            i++;
        }

        if (i < baseParts.length && i < parts.length) {
            for (int j = i; j < baseParts.length; j++) {
                buf.append("..").append('/');
            }
        }

        for (; i < parts.length; i++) {
            buf.append(parts[i]);

            if (i < parts.length - 1) {
                buf.append('/');
            }
        }

        if (endsWithSlash && buf.length() > 0 && buf.charAt(buf.length() - 1) != '/') {
            buf.append('/');
        }

        return buf.toString();
    }

    // ==========================================================================
    // 取得文件名后缀。
    // ==========================================================================

    /**
     * 取得文件路径的后缀。
     * <ul>
     * <li>未指定文件名 - 返回<code>null</code>。</li>
     * <li>文件名没有后缀 - 返回<code>null</code>。</li>
     * </ul>
     */
    public static String getExtension(String fileName, boolean toLowerCase) {
        return getExtension(fileName, null, toLowerCase);
    }

    /**
     * 取得文件路径的后缀。
     * <ul>
     * <li>未指定文件名 - 返回<code>null</code>。</li>
     * <li>文件名没有后缀 - 返回指定字符串<code>nullExt</code>。</li>
     * </ul>
     */
    public static String getExtension(String fileName, String nullExt) {
        return getExtension(fileName, nullExt, false);
    }

    /**
     * 取得文件路径的后缀。
     * <ul>
     * <li>未指定文件名 - 返回<code>null</code>。</li>
     * <li>文件名没有后缀 - 返回指定字符串<code>nullExt</code>。</li>
     * </ul>
     */
    public static String getExtension(String fileName, String nullExt, boolean toLowerCase) {
        fileName = trimToNull(fileName);

        if (fileName == null) {
            return null;
        }

        fileName = fileName.replace('\\', '/');
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);

        int index = fileName.lastIndexOf(".");
        String ext = null;

        if (index >= 0) {
            ext = trimToNull(fileName.substring(index + 1));
        }

        if (ext == null) {
            return nullExt;
        } else {
            return toLowerCase ? ext.toLowerCase() : ext;
        }
    }

    /**
     * 规格化文件名后缀。
     * <ul>
     * <li>除去两边空白。</li>
     * <li>转成小写。</li>
     * <li>除去开头的“<code>.</code>”。</li>
     * <li>对空白的后缀，返回<code>null</code>。</li>
     * </ul>
     */
    public static String normalizeExtension(String ext) {
        ext = trimToNull(ext);

        if (ext != null) {
            ext = ext.toLowerCase();

            if (ext.startsWith(".")) {
                ext = trimToNull(ext.substring(1));
            }
        }

        return ext;
    }

    /**
     * 根据指定url和相对路径，计算出相对路径所对应的完整url。类似于<code>URI.resolve()</code>
     * 方法，然后后者不能正确处理jar类型的URL。
     */
    public static String resolve(String url, String relativePath) throws Exception {
        url = trimToEmpty(url);

        Matcher m = schemePrefixPattern.matcher(url);
        int index = 0;

        if (m.find()) {
            index = m.end();

            if (url.charAt(index - 1) == '/') {
                index--;
            }
        }

        return url.substring(0, index) + normalizeAbsolutePath(url.substring(index) + "/../" + relativePath);
    }


    /**
     * 删除某个文件夹下的所有文件夹和文件
     *
     * @param delpath 待删除的文件夹路径
     */
    public static boolean deletefile(String delpath) throws Exception {
        delete(new File(delpath));
        return true;
    }

    /**
     * 删除某个文件夹下的所有文件夹和文件
     *
     * @param file 待删除的文件夹
     */
    public static void delete(File file) throws Exception {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    delete(files[i]);
                }
            }
            file.delete();
        }
    }
}
