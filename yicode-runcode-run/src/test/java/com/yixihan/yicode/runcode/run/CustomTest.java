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
        String encode = Base64.encode ("#include <stdio.h>\n" + "#include <ctype.h>\n" + "#include <string.h>\n" + "#include <stdlib.h>\n" + "\n" + "int isAllowed(char *s) {\n" + "    int i = 0;\n" + "    int length = 0;\n" + "    int hasNumber = 0;\n" + "\n" + "    if (isalpha(s[0]) == 0) {\n" + "        return -1;\n" + "    }\n" + "\n" + "    length = strlen(s);\n" + "\n" + "    for(i = 1; i < length; i++) {\n" + "        if (isalpha(s[i]) == 0) {\n" + "            if (isdigit(s[i]) == 0) {\n" + "                return -1;\n" + "            } else {\n" + "                hasNumber = 1;\n" + "            }\n" + "        }\n" + "    }\n" + "\n" + "    if (hasNumber == 0) {\n" + "        return -1;\n" + "    } else {\n" + "        return 0;\n" + "    }\n" + "}\n" + "\n" + "int main(int argc, char *argv[]) {\n" + "\n" + "    int num = 0;\n" + "    char str[21] = {0};\n" + "    int i = 0;\n" + "    int *numsAllowed = NULL;\n" + "\n" + "    scanf(\"%d\", &num);\n" + "\n" + "    numsAllowed = (int *) malloc(sizeof(int) * num);\n" + "    memset(numsAllowed, 0, sizeof(numsAllowed));\n" + "\n" + "    for (i = 0; i < num; i++) {\n" + "        memset(str, 0, sizeof(str));\n" + "        scanf(\"%s\", str);\n" + "\n" + "        if (isAllowed(str) < 0) {\n" + "            numsAllowed[i] = -1;\n" + "        } else {\n" + "            numsAllowed[i] = 0;\n" + "        }\n" + "    }\n" + "\n" + "    for (i = 0; i < num; i++) {\n" + "        if (numsAllowed[i] < 0) {\n" + "            printf(\"Wrong\\n\");\n" + "        } else {\n" + "            printf(\"Accept\\n\");\n" + "        }\n" + "    }\n" + "\n" + "    free(numsAllowed);\n" + "    numsAllowed = NULL;\n" + "\n" + "    return 0;\n" + "}");
        System.out.println (encode);
    
    }
}
