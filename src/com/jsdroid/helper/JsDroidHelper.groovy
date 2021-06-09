package com.jsdroid.helper

import com.jsdroid.commons.Http
import com.jsdroid.script.JsDroidScript

abstract class JsDroidHelper extends JsDroidScript{
    def rand = new Rand()
    def http = gHttp
}
