package com.jsdroid.helper

class Rand {
    static final char[] HexLower = ['0', '1', '2', '3', '4', '5',
                                    '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'];

    int randMax(int n) {
        Math.random() * n as int
    }

    int randBetween(int n, int m) {
        (Math.random() * (m - n + 1) + n) as int
    }

    boolean randBool() {
        //0|1
        randMax(2)
    }

    String randAZ(int count) {
        randAZ(count, false)
    }

    String randAZ(int count, boolean hasUpperCase) {
        String ret = ""
        if (hasUpperCase) {
            char a = 'a'
            char z = 'z'
            char A = 'A'
            char Z = 'Z'
            for (i in 0..<count) {
                if (randBool()) {
                    char s = randBetween(a as int, z as int)
                    ret += s
                } else {
                    char s = randBetween(A as int, Z as int)
                    ret += s
                }
            }
        } else {
            char a = 'a'
            char z = 'z'
            for (i in 0..<count) {
                char s = randBetween(a as int, z as int)
                ret += s
            }
        }
        return ret
    }

    String randHexString(int count) {
        String ret = ""
        for (i in 0..<count) {
            ret += HexLower[randMax(HexLower.length)]

        }
        return ret
    }

    String randHexString(int n, boolean toUpperCase) {
        if (toUpperCase) {
            randHexString(n).toUpperCase()
        } else {
            randHexString(n)
        }
    }

    String randUUID() {
        UUID.randomUUID().toString()
    }
}
