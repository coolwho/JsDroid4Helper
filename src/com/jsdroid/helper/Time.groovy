package com.jsdroid.helper

import com.jsdroid.script.JsDroidScript
import groovy.transform.CompileStatic

class Time {
    JsDroidScript script

    Time(JsDroidScript script) {
        this.script = script
    }


    @CompileStatic
    def timing(Runnable runnable){
        def stack = new Throwable().stackTrace
        long st = System.currentTimeMillis()
        runnable.run()
        long et = System.currentTimeMillis()
        if ("Method.java" == stack[1].fileName){
            script.print("代码耗时：" + (et - st) + "ms @${stack[7].fileName}#${stack[7].lineNumber}")
        }else{
            script.print("代码耗时：" + (et - st) + "ms @${stack[1].fileName}#${stack[1].lineNumber}")
        }
    }
}
