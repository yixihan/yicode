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
        String c = Base64.encode ("#includ <stdio.h>\n" + "#include <ctype.h>\n" + "#include <string.h>\n" + "#include <stdlib.h>\n" + "\n" + "int isAllowed(char *s) {\n" + "    int i = 0;\n" + "    int length = 0;\n" + "    int hasNumber = 0;\n" + "\n" + "    if (isalpha(s[0]) == 0) {\n" + "        return -1;\n" + "    }\n" + "\n" + "    length = strlen(s);\n" + "\n" + "    for(i = 1; i < length; i++) {\n" + "        if (isalpha(s[i]) == 0) {\n" + "            if (isdigit(s[i]) == 0) {\n" + "                return -1;\n" + "            } else {\n" + "                hasNumber = 1;\n" + "            }\n" + "        }\n" + "    }\n" + "\n" + "    if (hasNumber == 0) {\n" + "        return -1;\n" + "    } else {\n" + "        return 0;\n" + "    }\n" + "}\n" + "\n" + "int main(int argc, char *argv[]) {\n" + "\n" + "    int num = 0;\n" + "    char str[21] = {0};\n" + "    int i = 0;\n" + "    int *numsAllowed = NULL;\n" + "\n" + "    scanf(\"%d\", &num);\n" + "\n" + "    numsAllowed = (int *) malloc(sizeof(int) * num);\n" + "    memset(numsAllowed, 0, sizeof(numsAllowed));\n" + "\n" + "    for (i = 0; i < num; i++) {\n" + "        memset(str, 0, sizeof(str));\n" + "        scanf(\"%s\", str);\n" + "\n" + "        if (isAllowed(str) < 0) {\n" + "            numsAllowed[i] = -1;\n" + "        } else {\n" + "            numsAllowed[i] = 0;\n" + "        }\n" + "    }\n" + "\n" + "    for (i = 0; i < num; i++) {\n" + "        if (numsAllowed[i] < 0) {\n" + "            printf(\"Wrong\\n\");\n" + "        } else {\n" + "            printf(\"Accept\\n\");\n" + "        }\n" + "    }\n" + "\n" + "    free(numsAllowed);\n" + "    numsAllowed = NULL;\n" + "\n" + "    return 0;\n" + "}");
        String cCompileErr = Base64.encode ("#include <stdio.h>\n" + "#include <ctype.h>\n" + "#include <string.h>\n" + "#include <stdlib.h>\n" + "\n" + "int isAllowed(char *s) {\n" + "    int i = 0;\n" + "    int length = 0;\n" + "    int hasNumber = 0;\n" + "\n" + "    if (isalpha(s[0]) == 0) {\n" + "        return -1;\n" + "    }\n" + "\n" + "    length = strlen(s);\n" + "\n" + "    for(i = 1; i < length; i++) {\n" + "        if (isalpha(s[i]) == 0) {\n" + "            if (isdigit(s[i]) == 0) {\n" + "                return -1;\n" + "            } else {\n" + "                hasNumber = 1;\n" + "            }\n" + "        }\n" + "    }\n" + "\n" + "    if (hasNumber == 0) {\n" + "        return -1;\n" + "    } else {\n" + "        return 0;\n" + "    }\n" + "}\n" + "\n" + "int main(int argc, char *argv[]) {\n" + "\n" + "    int num = 0;\n" + "    char str[21] = {0};\n" + "    int i = 0;\n" + "    int *numsAllowed = NULL;\n" + "\n" + "    scanf(\"%d\", &num);\n" + "\n" + "    numsAllowed = (int *) malloc(sizeof(int) * num);\n" + "    memset(numsAllowed, 0, sizeof(numsAllowed));\n" + "\n" + "    for (i = 0; i < num; i++) {\n" + "        memset(str, 0, sizeof(str));\n" + "        scanf(\"%s\", str);\n" + "\n" + "        if (isAllowed(str) < 0) {\n" + "            numsAllowed[i] = -1;\n" + "        } else {\n" + "            numsAllowed[i] = 0;\n" + "        }\n" + "    }\n" + "\n" + "    for (i = 0; i < num; i++) {\n" + "        if (numsAllowed[i] < 0) {\n" + "            printf(\"Wrong\\n\");\n" + "        } else {\n" + "            printf(\"Accept\\n\");\n" + "        }\n" + "    }\n" + "\n" + "    free(numsAllowed);\n" + "    numsAllowed = NULL;\n" + "\n" + "    return 0;\n" + "}");
        String go = Base64.encode ("\n" + "package main\n" + "import (\n" + "    \"fmt\"\n" + "    \"unicode\"\n" + ")\n" + "\n" + "func check(s string)string{\n" + "    d := false\n" + "    for i,c := range s{\n" + "        if i==0{\n" + "            if !unicode.IsLetter(c){\n" + "                return \"Wrong\"\n" + "            }\n" + "        }\n" + "        if !unicode.IsLetter(c) &&!unicode.IsNumber(c){\n" + "            return \"Wrong\"\n" + "        }else if unicode.IsNumber(c){\n" + "            d = true\n" + "        }\n" + "    }\n" + "    if d{\n" + "        return \"Accept\"\n" + "    }else{\n" + "        return \"Wrong\"\n" + "    }\n" + "}\n" + "\n" + "func main(){\n" + "    var T int\n" + "    var s string\n" + "    fmt.Scanln(&T)\n" + "    for i:=0;i<T;i++{\n" + "        fmt.Scanln(&s)\n" + "        fmt.Printf(\"%s\\n\",check(s))\n" + "    }\n" + "}");
        String goCompileErr = Base64.encode ("\n" + "package main\n" + "import (\n" + "    \"fmt\"\n" + "    \"unicode\"\n" + ")\n" + "\n" + "func check(s string)string{\n" + "    d := false\n" + "    for i,c := range s{\n" + "        if i==0{\n" + "            if !unicode.IsLetter(c){\n" + "                return \"Wrong\"\n" + "            }\n" + "        }\n" + "        if !unicode.IsLetter(c) &&!unicode.IsNumber(c){\n" + "            return \"Wrong\"\n" + "        }else if unicode.IsNumber(c){\n" + "            d = true\n" + "        \n" + "    }\n" + "    if d{\n" + "        return \"Accept\"\n" + "    }else{\n" + "        return \"Wrong\"\n" + "    }\n" + "}\n" + "\n" + "func main(){\n" + "    var T int\n" + "    var s string\n" + "    fmt.Scanln(&T)\n" + "    for i:=0;i<T;i++{\n" + "        fmt.Scanln(&s)\n" + "        fmt.Printf(\"%s\\n\",check(s))\n" + "    }\n" + "}");
        
        String java = Base64.encode ("import java.util.ArrayList;\n" + "import java.util.List;\n" + "import java.util.Scanner;\n" + "\n" + "public class Main {\n" + "    // 输入函数，用于把输入的结果放到集合中\n" + "    public static List<String> getInput() {\n" + "        Scanner scanner = new Scanner(System.in);\n" + "        // 次数，行数\n" + "        int times = scanner.nextInt();\n" + "        List<String> list = new ArrayList<>();\n" + "        for (int i = 0; i < times; i++) {\n" + "            // 每一行\n" + "            list.add(scanner.next());\n" + "        }\n" + "        return list;\n" + "    }\n" + "\n" + "    // 输出函数，用于把结果集合转变为输出\n" + "    public static void output(List<Boolean> result) {\n" + "        for (Boolean s : result) {\n" + "            // 根据 s 的 true 和 false 来返回\n" + "            System.out.println(s ? \"Accept\" : \"Wrong\");\n" + "        }\n" + "    }\n" + "\n" + "    // 用户名的首字符必须是大写或者小写字母。\n" + "    // 用户名只能包含大小写字母，数字。\n" + "    // 用户名需要包含至少一个字母和一个数字。\n" + "    public static boolean check(String input) {\n" + "        // 用户名需要包含至少一个字母和一个数字，长度至少为2\n" + "        if (input.length() < 2)\n" + "            return false;\n" + "\n" + "        // 用户名的首字符必须是大写或者小写字母。\n" + "        char firstLetter = input.charAt(0);\n" + "        if (!(firstLetter >= 'a' && firstLetter <= 'z') && !(firstLetter >= 'A' && firstLetter <= 'Z'))\n" + "            return false;\n" + "\n" + "        // 数字的个数\n" + "        int numOfNums = 0;\n" + "        for (char ch : input.toCharArray()) {\n" + "            // 用户名只能包含大小写字母，数字。\n" + "            // 统计数字个数\n" + "            if (ch >= '0' && ch <= '9')\n" + "                numOfNums++;\n" + "                // 用户名只能包含大小写字母，数字。\n" + "            else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))\n" + "                continue;\n" + "            else\n" + "                return false;\n" + "        }\n" + "        // 用户名的首字符必须是大写或者小写字母了，所以只需要判断条件至少一个数字。\n" + "        return numOfNums != 0;\n" + "    }\n" + "\n" + "    public static void main(String[] args) {\n" + "        List<String> list = getInput();\n" + "        List<Boolean> res = new ArrayList<> ();\n" + "        for (String eachTry : list) {\n" + "            res.add(check(eachTry));\n" + "        }\n" + "        output(res);\n" + "    }\n" + "}");
        String javaCompileErr = Base64.encode ("import java.util.ArrayList;\n" + "import java.util.List;\n" + "import java.util.Scanner;\n" + "\n" + "public class Main {\n" + "    // 输入函数，用于把输入的结果放到集合中\n" + "    public static List<String> getInput() {\n" + "        Scanner scanner = new Scanner(System.in);\n" + "        // 次数，行数\n" + "        int times = scanner.nextInt();\n" + "        List<String> list = new ArrayList<>();\n" + "        for (int i = 0; i < times; i++) \n" + "            // 每一行\n" + "            list.add(scanner.next());\n" + "        }\n" + "        return list;\n" + "    }\n" + "\n" + "    // 输出函数，用于把结果集合转变为输出\n" + "    public static void output(List<Boolean> result) {\n" + "        for (Boolean s : result) {\n" + "            // 根据 s 的 true 和 false 来返回\n" + "            System.out.println(s ? \"Accept\" : \"Wrong\");\n" + "        }\n" + "    }\n" + "\n" + "    // 用户名的首字符必须是大写或者小写字母。\n" + "    // 用户名只能包含大小写字母，数字。\n" + "    // 用户名需要包含至少一个字母和一个数字。\n" + "    public static boolean check(String input) {\n" + "        // 用户名需要包含至少一个字母和一个数字，长度至少为2\n" + "        if (input.length() < 2)\n" + "            return false;\n" + "\n" + "        // 用户名的首字符必须是大写或者小写字母。\n" + "        char firstLetter = input.charAt(0);\n" + "        if (!(firstLetter >= 'a' && firstLetter <= 'z') && !(firstLetter >= 'A' && firstLetter <= 'Z'))\n" + "            return false;\n" + "\n" + "        // 数字的个数\n" + "        int numOfNums = 0;\n" + "        for (char ch : input.toCharArray()) {\n" + "            // 用户名只能包含大小写字母，数字。\n" + "            // 统计数字个数\n" + "            if (ch >= '0' && ch <= '9')\n" + "                numOfNums++;\n" + "                // 用户名只能包含大小写字母，数字。\n" + "            else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))\n" + "                continue;\n" + "            else\n" + "                return false;\n" + "        }\n" + "        // 用户名的首字符必须是大写或者小写字母了，所以只需要判断条件至少一个数字。\n" + "        return numOfNums != 0;\n" + "    }\n" + "\n" + "    public static void main(String[] args) {\n" + "        List<String> list = getInput();\n" + "        List<Boolean> res = new ArrayList<> ();\n" + "        for (String eachTry : list) {\n" + "            res.add(check(eachTry));\n" + "        }\n" + "        output(res);\n" + "    }\n" + "}");
        String javaRunErr = Base64.encode ("import java.util.ArrayList;\n" + "import java.util.List;\n" + "import java.util.Scanner;\n" + "\n" + "public class Main {\n" + "    // 输入函数，用于把输入的结果放到集合中\n" + "    public static List<String> getInput() {\n" + "        Scanner scanner = new Scanner(System.in);\n" + "        // 次数，行数\n" + "        int times = scanner.nextInt();\n" + "        List<String> list = new ArrayList<>();\n" + "        for (int i = 0; i < times; i++) {\n" + "            // 每一行\n" + "            list.add(scanner.next());\n" + "        }\n" + "        return list;\n" + "    }\n" + "\n" + "    // 输出函数，用于把结果集合转变为输出\n" + "    public static void output(List<Boolean> result) {\n" + "        for (Boolean s : result) {\n" + "            // 根据 s 的 true 和 false 来返回\n" + "            System.out.println(s ? \"Accept\" : \"Wrong\");\n" + "        }\n" + "    }\n" + "\n" + "    // 用户名的首字符必须是大写或者小写字母。\n" + "    // 用户名只能包含大小写字母，数字。\n" + "    // 用户名需要包含至少一个字母和一个数字。\n" + "    public static boolean check(String input) {\n" + "        // 用户名需要包含至少一个字母和一个数字，长度至少为2\n" + "        if (input.length() < 2)\n" + "            return false;\n" + "\n" + "        // 用户名的首字符必须是大写或者小写字母。\n" + "        char firstLetter = input.charAt(0);\n" + "        if (!(firstLetter >= 'a' && firstLetter <= 'z') && !(firstLetter >= 'A' && firstLetter <= 'Z'))\n" + "            return false;\n" + "\n" + "        // 数字的个数\n" + "        int numOfNums = 0;\n" + "        for (char ch : input.toCharArray()) {\n" + "            // 用户名只能包含大小写字母，数字。\n" + "            // 统计数字个数\n" + "            if (ch >= '0' && ch <= '9')\n" + "                numOfNums++;\n" + "                // 用户名只能包含大小写字母，数字。\n" + "            else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))\n" + "                continue;\n" + "            else\n" + "                return false;\n" + "        }\n" + "        // 用户名的首字符必须是大写或者小写字母了，所以只需要判断条件至少一个数字。\n" + "        return numOfNums != 0;\n" + "    }\n" + "\n" + "    public static void main(String[] args) {\n" + "        List<String> list = getInput();\n" + "        List<Boolean> res = new ArrayList<> ();\n" + "        for (String eachTry : list) {\n" + "            res.add(check(eachTry));\n" + "        }\n" + "        int a = 10 / 0;\n" + "        output(res);\n" + "    }\n" + "}");
    
        System.out.println ("c :\n" + c + "\n");
        System.out.println ("cCompileErr :\n" + cCompileErr + "\n");
        System.out.println ("go :\n" + go + "\n");
        System.out.println ("goCompileErr :\n" + goCompileErr + "\n");
        System.out.println ("java :\n" + java + "\n");
        System.out.println ("javaCompileErr :\n" + javaCompileErr + "\n");
        System.out.println ("javaRunErr :\n" + javaRunErr + "\n");

        
    }
}
