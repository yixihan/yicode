package com.yixihan.yicode.runcode.run;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.io.File;

/**
 * description
 *
 * @author yixihan
 * @date 2023/1/3 17:08
 */
public class CustomTest {
    
    @Test
    public void testdeletePath (){
        File path = new File ("D:\\test");
        FileUtil.del (path);
    }
    
    @Test
    public void encodingCode () {
        String encode = Base64.encode ("import java.util.ArrayList;\n" + "import java.util.List;\n" + "import java.util.Scanner;\n" + "\n" + "class Solution {\n" + "    // 输入函数，用于把输入的结果放到集合中\n" + "    public static List<String> getInput() {\n" + "        Scanner scanner = new Scanner(System.in);\n" + "        // 次数，行数\n" + "        int times = scanner.nextInt();\n" + "        List<String> list = new ArrayList<>();\n" + "        for (int i = 0; i < times; i++) {\n" + "            // 每一行\n" + "            list.add(scanner.next());\n" + "        }\n" + "        return list;\n" + "    }\n" + "\n" + "    // 输出函数，用于把结果集合转变为输出\n" + "    public static void output(List<Boolean> result) {\n" + "        for (Boolean s : result) {\n" + "            // 根据 s 的 true 和 false 来返回\n" + "            System.out.println(s ? \"Accept\" : \"Wrong\");\n" + "        }\n" + "    }\n" + "\n" + "    // 用户名的首字符必须是大写或者小写字母。\n" + "    // 用户名只能包含大小写字母，数字。\n" + "    // 用户名需要包含至少一个字母和一个数字。\n" + "    public static boolean check(String input) {\n" + "        // 用户名需要包含至少一个字母和一个数字，长度至少为2\n" + "        if (input.length() < 2)\n" + "            return false;\n" + "\n" + "        // 用户名的首字符必须是大写或者小写字母。\n" + "        char firstLetter = input.charAt(0);\n" + "        if (!(firstLetter >= 'a' && firstLetter <= 'z') && !(firstLetter >= 'A' && firstLetter <= 'Z'))\n" + "            return false;\n" + "\n" + "        // 数字的个数\n" + "        int numOfNums = 0;\n" + "        for (char ch : input.toCharArray()) {\n" + "            // 用户名只能包含大小写字母，数字。\n" + "            // 统计数字个数\n" + "            if (ch >= '0' && ch <= '9')\n" + "                numOfNums++;\n" + "                // 用户名只能包含大小写字母，数字。\n" + "            else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))\n" + "                continue;\n" + "            else\n" + "                return false;\n" + "        }\n" + "        // 用户名的首字符必须是大写或者小写字母了，所以只需要判断条件至少一个数字。\n" + "        return numOfNums != 0;\n" + "    }\n" + "\n" + "    public static void main(String[] args) {\n" + "        List<String> list = getInput();\n" + "        List<Boolean> res = new ArrayList<> ();\n" + "        for (String eachTry : list) {\n" + "            res.add(check(eachTry));\n" + "        }\n" + "        output(res);\n" + "    }\n" + "}");
        System.out.println (encode);
    
    }
}
