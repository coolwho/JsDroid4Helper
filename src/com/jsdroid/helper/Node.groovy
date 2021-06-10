package com.jsdroid.helper

import android.graphics.Rect
import com.jsdroid.script.JsDroidScript
import com.jsdroid.sdk.nodes.NodeSearch
import com.jsdroid.sdk.nodes.Nodes
import com.jsdroid.uiautomator2.UiObject
import com.jsdroid.uiautomator2.UiObject2

class Node {
    private JsDroidScript script;

    Node(JsDroidScript script) {
        this.script = script
    }

    boolean inRect(com.jsdroid.sdk.nodes.Node node, int left, int top, int right, int bottom) {
        new Rect(left, top, right, bottom).contains(node.rect)
    }

    boolean inRect(UiObject object, int left, int top, int right, int bottom) {
        new Rect(left, top, right, bottom).contains(object.getVisibleBounds())
    }

    boolean inRect(UiObject2 object, int left, int top, int right, int bottom) {
        new Rect(left, top, right, bottom).contains(object.getVisibleBounds())
    }

    com.jsdroid.sdk.nodes.Node findOneInRect(Rect rect, Map moreInfo = new HashMap()) {
        com.jsdroid.sdk.nodes.Node ret = null
        script.tryDo {
            def search = Nodes.getInstance().map(moreInfo)
            Nodes.getInstance().eachNode {
                if (rect.contains(it.rect)) {
                    if (search.compare(it)) {
                        ret = it
                        return true
                    }
                }
                return false
            }
        }
        return ret
    }

    com.jsdroid.sdk.nodes.Node findOneInRect(int left, int top, int right, int bottom, Map moreInfo = new HashMap()) {
        findOneInRect(new Rect(left, top, right, bottom), moreInfo)
    }

    List<com.jsdroid.sdk.nodes.Node> findAllInRect(int left, int top, int right, int bottom
                                                   , Map moreInfo = new HashMap()) {
        findAllInRect(new Rect(left, top, right, bottom), moreInfo)
    }

    List<com.jsdroid.sdk.nodes.Node> findAllInRect(Rect rect, Map moreInfo = new HashMap()) {
        def ret = new ArrayList<com.jsdroid.sdk.nodes.Node>()
        script.tryDo {
            def search = Nodes.getInstance().map(moreInfo)
            Nodes.getInstance().eachNode {
                if (rect.contains(it.rect)) {
                    if (search.compare(it)) {
                        ret.add(it)
                        return false
                    }
                }
                return false
            }
        }
        return ret
    }

    NodeSearch search() {
        return new NodeSearch()
    }


}
