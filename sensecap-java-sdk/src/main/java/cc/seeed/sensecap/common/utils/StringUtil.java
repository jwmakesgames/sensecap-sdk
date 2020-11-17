package cc.seeed.sensecap.common.utils;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Random RANDOM = new Random();

    public static byte[] hexToByteArray(final String s) {
        final byte[] ret = new byte[s.length() / 2];
        for (int i = 0; i < ret.length; i++) {
            final int begin = i * 2;
            ret[i] = (byte) Integer.parseInt(s.substring(begin, begin + 2), 16);
        }
        return ret;
    }

    public static boolean isSameDay(final long lastTime) {
        final Calendar now = Calendar.getInstance();
        final Calendar last = Calendar.getInstance();
        last.setTimeInMillis(lastTime);

        now.clear(Calendar.MILLISECOND);
        now.clear(Calendar.SECOND);
        now.clear(Calendar.MINUTE);
        now.set(Calendar.HOUR_OF_DAY, 0);

        last.clear(Calendar.MILLISECOND);
        last.clear(Calendar.SECOND);
        last.clear(Calendar.MINUTE);
        last.set(Calendar.HOUR_OF_DAY, 0);

        return now.equals(last);
    }

    /**
     * <p>
     * Creates a random string whose length is the number of characters
     * specified.
     * </p>
     *
     * <p>
     * Characters will be chosen from the set of all characters.
     * </p>
     *
     * @param count the length of random string to create
     * @return the random string
     */
    public static String random(final int count) {
        return random(count, false, false);
    }

    /**
     * <p>
     * Creates a random string whose length is the number of characters
     * specified.
     * </p>
     *
     * <p>
     * Characters will be chosen from the set of alpha-numeric characters as
     * indicated by the arguments.
     * </p>
     *
     * @param count   the length of random string to create
     * @param letters if <code>true</code>, generated string will include alphabetic
     *                characters
     * @param numbers if <code>true</code>, generated string will include numeric
     *                characters
     * @return the random string
     */
    public static String random(final int count, final boolean letters, final boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }

    /**
     * <p>
     * Creates a random string whose length is the number of characters
     * specified.
     * </p>
     *
     * <p>
     * Characters will be chosen from the set of alpha-numeric characters as
     * indicated by the arguments.
     * </p>
     *
     * @param count   the length of random string to create
     * @param start   the position in set of chars to start at
     * @param end     the position in set of chars to end before
     * @param letters if <code>true</code>, generated string will include alphabetic
     *                characters
     * @param numbers if <code>true</code>, generated string will include numeric
     *                characters
     * @return the random string
     */
    public static String random(final int count, final int start, final int end, final boolean letters, final boolean numbers) {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }

    /**
     * <p>
     * Creates a random string based on a variety of options, using supplied
     * source of randomness.
     * </p>
     *
     * <p>
     * If start and end are both <code>0</code>, start and end are set to
     * <code>' '</code> and <code>'z'</code>, the ASCII printable characters,
     * will be used, unless letters and numbers are both <code>false</code>, in
     * which case, start and end are set to <code>0</code> and
     * <code>Integer.MAX_VALUE</code>.
     *
     * <p>
     * If set is not <code>null</code>, characters between start and end are
     * chosen.
     * </p>
     *
     * <p>
     * This method accepts a user-supplied {@link Random} instance to use as a
     * source of randomness. By seeding a single {@link Random} instance with a
     * fixed seed and using it for each call, the same random sequence of
     * strings can be generated repeatedly and predictably.
     * </p>
     *
     * @param count   the length of random string to create
     * @param start   the position in set of chars to start at
     * @param end     the position in set of chars to end before
     * @param letters only allow letters?
     * @param numbers only allow numbers?
     * @param chars   the set of chars to choose randoms from. If <code>null</code>,
     *                then it will use the set of all chars.
     * @param random  a source of randomness.
     * @return the random string
     * @throws ArrayIndexOutOfBoundsException if there are not <code>(end - start) + 1</code> characters in
     *                                        the set array.
     * @throws IllegalArgumentException       if <code>count</code> &lt; 0.
     * @since 2.0
     */
    public static String random(int count, int start, int end, final boolean letters, final boolean numbers, final char[] chars, final Random random) {
        if (count == 0) {
            return "";
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        if ((start == 0) && (end == 0)) {
            end = 'z' + 1;
            start = ' ';
            if (!letters && !numbers) {
                start = 0;
                end = Integer.MAX_VALUE;
            }
        }

        final char[] buffer = new char[count];
        final int gap = end - start;

        while (count-- != 0) {
            char ch;
            if (chars == null) {
                ch = (char) (random.nextInt(gap) + start);
            } else {
                ch = chars[random.nextInt(gap) + start];
            }
            if ((letters && Character.isLetter(ch)) || (numbers && Character.isDigit(ch)) || (!letters && !numbers)) {
                if ((ch >= 56320) && (ch <= 57343)) {
                    if (count == 0) {
                        count++;
                    } else {
                        // low surrogate, insert high surrogate after putting it
                        // in
                        buffer[count] = ch;
                        count--;
                        buffer[count] = (char) (55296 + random.nextInt(128));
                    }
                } else if ((ch >= 55296) && (ch <= 56191)) {
                    if (count == 0) {
                        count++;
                    } else {
                        // high surrogate, insert low surrogate before putting
                        // it in
                        buffer[count] = (char) (56320 + random.nextInt(128));
                        count--;
                        buffer[count] = ch;
                    }
                } else if ((ch >= 56192) && (ch <= 56319)) {
                    // private high surrogate, no effing clue, so skip it
                    count++;
                } else {
                    buffer[count] = ch;
                }
            } else {
                count++;
            }
        }
        return new String(buffer);
    }

    /**
     * <p>
     * Creates a random string whose length is the number of characters
     * specified.
     * </p>
     *
     * <p>
     * Characters will be chosen from the set of alpha-numeric characters.
     * </p>
     *
     * @param count the length of random string to create
     * @return the random string
     */
    public static String randomAlphanumeric(final int count) {
        return random(count, true, true);
    }

    /**
     * <p>
     * Creates a random string whose length is the number of characters
     * specified.
     * </p>
     *
     * <p>
     * Characters will be chosen from the set of characters whose ASCII value is
     * between <code>32</code> and <code>126</code> (inclusive).
     * </p>
     *
     * @param count the length of random string to create
     * @return the random string
     */
    public static String randomAscii(final int count) {
        return random(count, 32, 127, false, false);
    }

    public static String randomNumeric(final int count) {
        return random(count, false, true);
    }

    /**
     * convert byte array to hexadecimal string
     */
    public static String toHexString(final byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            buf.append(String.format("%02X", bytes[i]));
        }
        return buf.toString();
    }

    private StringUtil() {

    }

    /**
     * getInt
     *
     * @param str
     * @return
     */
    public final static int getInt(String str) {
        str = getString(str);
        if (!isNumber(str)) {
            return 0;
        }
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(str.split("\\.")[0]);
    }

    /**
     * getInt
     *
     * @param
     * @return
     */
    public final static int getInt(Object obj) {
        if (obj == null) {
            return 0;
        }
        return getInt(obj.toString().trim());
    }

    /**
     * getByte
     *
     * @param str
     * @return
     * @author ruan
     */
    public final static byte getByte(String str) {
        str = getString(str);
        if (!isNumber(str)) {
            return 0;
        }
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return Byte.parseByte(str.split("\\.")[0]);
    }

    /**
     * getByte
     *
     * @param
     * @return
     * @author ruan
     */
    public final static byte getByte(Object obj) {
        if (obj == null) {
            return 0;
        }
        return getByte(obj.toString().trim());
    }

    /**
     * getShort
     *
     * @param str
     * @return
     * @author ruan
     */
    public final static short getShort(String str) {
        str = getString(str);
        if (!isNumber(str)) {
            return 0;
        }
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return Short.parseShort(str.split("\\.")[0]);
    }

    /**
     * getShort
     *
     * @param
     * @return
     * @author ruan
     */
    public final static short getShort(Object obj) {
        if (obj == null) {
            return 0;
        }
        return getShort(obj.toString().trim());
    }

    /**
     * getLong
     *
     * @param str
     * @return
     */
    public final static long getLong(String str) {
        str = getString(str);
        if (!isNumber(str)) {
            return 0;
        }
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return Long.parseLong(str.split("\\.")[0]);
    }

    /**
     * getLong
     *
     * @param
     * @return
     */
    public final static long getLong(Object obj) {
        if (obj == null) {
            return 0;
        }
        return getLong(obj.toString().trim());
    }

    /**
     * getDouble
     *
     * @param str
     * @return
     */
    public final static double getDouble(String str) {
        str = getString(str);
        if (!isNumber(str)) {
            return 0;
        }
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(str);
    }

    /**
     * getDouble
     *
     * @param
     * @return
     */
    public final static double getDouble(Object obj) {
        if (obj == null) {
            return 0;
        }
        return getDouble(obj.toString().trim());
    }

    /**
     * getFloat
     *
     * @param str
     * @return
     */
    public final static float getFloat(String str) {
        str = getString(str);
        if (!isNumber(str)) {
            return 0;
        }
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return Float.parseFloat(str);
    }

    /**
     * getFloat
     *
     * @param
     * @return
     */
    public final static float getFloat(Object obj) {
        if (obj == null) {
            return 0;
        }
        return getFloat(obj.toString().trim());
    }

    /**
     * getBoolean
     *
     * @param str
     * @return
     */
    public final static boolean getBoolean(String str) {
        str = getString(str);
        if (str == null || str.isEmpty()) {
            return false;
        }
        return Boolean.valueOf(str);
    }

    /**
     * getBoolean
     *
     * @param
     * @return
     */
    public final static boolean getBoolean(Object obj) {
        if (obj == null) {
            return false;
        }
        return getBoolean(obj.toString().trim());
    }

    /**
     * getString
     *
     * @param str
     * @return
     * @author ruan
     */
    public final static String getString(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * getString
     *
     * @param
     * @return
     * @author ruan
     */
    public final static String getString(Object obj) {
        if (obj == null) {
            return "";
        }
        return getString(obj.toString());
    }

    /**
     * 判断该字符串是否为ip
     *
     * @param ip
     * @return
     */
    public final static boolean isIp(String ip) {
        return ip.trim().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
    }

    /**
     * 检查字符串是否为一个数字
     *
     * @param value
     * @return
     * @author ruan
     */
    public final static boolean isNumber(Object value) {
        if (value == null) {
            return false;
        }
        return value.toString().trim().matches("\\-?[0-9]+(\\.[0-9]+)?");
    }

    /**
     * 把异常信息打印
     *
     * @param e
     * @return
     * @author ruan 2013-8-9
     */
    public final static String getStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String ret = sw.toString();
        pw.close();
        try {
            sw.close();
        } catch (IOException e1) {
        }
        return ret == null ? "" : ret;
    }

    /**
     * 计算子字符串在父字符串出现的次数
     *
     * @param subject
     * @param search
     * @return
     * @author ruan
     */
    public final static int strCount(String subject, String search) {
        String[] arr = subject.toLowerCase().split(search.toLowerCase());
        return arr.length > 0 ? arr.length - 1 : 0;
    }

    /**
     * 针对paramMap decode
     */
    public static Map<String, String[]> decodeParamMap(Map<String, String[]> param) {
        Map<String, String[]> map = new HashMap<>();
        Set<Entry<String, String[]>> set = param.entrySet();
        for (Entry<String, String[]> entry : set) {
            String key = entry.getKey();
            String[] arrValue = entry.getValue();
            if (arrValue == null || arrValue.length == 0) {
                map.put(key, arrValue);
                continue;
            }
            String[] newArr = new String[arrValue.length];
            for (int i = 0; i < arrValue.length; i++) {
                try {
                    newArr[i] = URLDecoder.decode(arrValue[i], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            map.put(key, newArr);
        }
        return map;
    }

    public static String dealKeyStr(String key) {
        Pattern pattern = Pattern.compile("\\d+$");
        Matcher matcher = pattern.matcher(key);
        if (matcher.find()) {
            return key.substring(0, key.lastIndexOf(":") + 1);
        }
        return key;
    }

    public static boolean isEmptyString(String str) {

        if (str == null) {
            return true;
        }

        if (str.trim().length() == 0) {

            return true;
        }

        return false;

    }

    /*
     * 科学计数法,让999999变成999,999格式
     * @author nick
     * @param iniNum 传入数字
     * @param split 需要分割的位数
     */
    public static String formatNumber(int iniNum, int split) {
        String numStr = String.valueOf(iniNum);
        StringBuffer tmp = new StringBuffer().append(numStr.replaceAll(",", "")).reverse(); //去掉所有逗号，并把串倒过来。
        //替换这样的串：连续split位数字的串，其右边还有个数字，在串的右边添加逗号
        String retNum = Pattern.compile("(\\d{" + split + "})(?=\\d)")
                .matcher(tmp.toString()).replaceAll("$1,");
        //替换完后，再把串倒回去返回
        return new StringBuffer().append(retNum).reverse().toString();
    }

    public static String convertStr2Persent(String numStr) {
        if ("0.0000".equalsIgnoreCase(numStr) || "0.00".equalsIgnoreCase(numStr)) {
            return "0.00";
        }
        boolean isFu = false;
        if (numStr.startsWith("-")) {
            numStr = numStr.substring(1);
            isFu = true;
        }
        char[] arr = numStr.toCharArray();

        StringBuffer sb = new StringBuffer();

        if ((String.valueOf(arr[0])).equalsIgnoreCase("1")) {
            return "100";
        } else {
            if (arr[2] == '0') {
                if (arr[3] == '0') {
                    sb.append('0').append('.').append(arr[4]).append(arr[5]);
                } else {
                    sb.append(arr[3]).append('.').append(arr[4]).append(arr[5]);
                }
            } else {

                sb.append(arr[2]).append(arr[3]).append('.').append(arr[4]).append(arr[5]);
            }
        }

        String str = sb.toString();
        if (isFu) {
            str = "-" + str;
        }
        return str;
    }


    public static String removeRoutePrefix(String path) {
        String substring = path.substring(1, path.length());
        int i = substring.indexOf("/");
        return path.substring(i + 1, path.length());
    }

    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])| (17[0,1,3,5-8]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    public static boolean emailFormat(String email) {
        boolean tag = true;
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            tag = false;
        }
        return tag;
    }


    private static String autoGenericCode(String code) {
        String result = "";
        // 保留code的位数
        result = String.format("%0" + code.length() + "d", Integer.parseInt(code));

        return result;
    }

    public static String autoGenericCode(String code, int num) {
        String result = "";

        // num 代表长度为4
        // d 代表参数为正数型
        result = String.format("%0" + num + "d", Integer.parseInt(code));

        return result;
    }

    /**
     * 23      * 两个double类型的数相除，保留两位小数
     * 24      * @param a
     * 25      * @param b
     * 26      * @param scale
     * 27      * @return
     * 28
     */
    public static double divide(double a, int b, int scale) {
        BigDecimal bd1 = new BigDecimal(Double.toString(a));
        BigDecimal bd2 = new BigDecimal(Integer.toString(b));
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }





    public static void main(String[] args) {

       // System.out.println(autoGenericCode("2", 6));
        System.out.println(divide(45.772D,3,3));
    }

}
