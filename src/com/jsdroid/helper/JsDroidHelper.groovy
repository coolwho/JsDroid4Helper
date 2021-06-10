package com.jsdroid.helper


import com.jsdroid.script.JsDroidScript
import com.jsdroid.sdk.https.Https

abstract class JsDroidHelper extends JsDroidScript {
    Rand rand = new Rand()
    Https http = gHttp
    MySQL mysql = new MySQL()
    Node node = new Node(this)
    Time time = new Time(this)
}
