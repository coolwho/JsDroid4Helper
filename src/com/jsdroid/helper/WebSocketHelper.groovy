package com.jsdroid.helper


import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake

import java.nio.ByteBuffer

/**
 * websocket 功能库
 * jar: Java-WebSocket-1.5.2.jar
 */
class WebSocketHelper {
    WebSocket create(String url) {
        new WebSocket(url)
    }

    interface IOnMessage {
        void onMessage(WebSocket webSocket, String message)
    }

    interface IOnBytesMassage {
        void onMessage(WebSocket webSocket, byte[] message)
    }

    interface IOnOpen {
        void onOpen(WebSocket webSocket, ServerHandshake handshake)
    }

    interface IOnClose {
        void onClose(WebSocket webSocket, int code, String reason, boolean remote)
    }

    interface IOnError {
        void onError(WebSocket webSocket, Exception e)
    }

    class WebSocket {
        private String url
        private WebSocketClient webSocketClient
        private IOnMessage onMessage
        private IOnBytesMassage onBytesMassage
        private IOnOpen onOpen
        private IOnClose onClose
        private IOnError onError

        WebSocket(String url) {
            this.url = url
        }

        WebSocket onOpen(IOnOpen onOpen) {
            synchronized (this) {
                this.onOpen = onOpen
                return this
            }
        }

        WebSocket onMessage(IOnMessage onMessage) {
            synchronized (this) {
                this.onMessage = onMessage
            }
            return this
        }

        WebSocket onBytesMessage(IOnBytesMassage onBytesMassage) {
            synchronized (this) {
                this.onBytesMassage = onBytesMassage
            }
            return this
        }

        WebSocket onClose(IOnClose onClose) {
            synchronized (this) {
                this.onClose = onClose
            }
            return this
        }

        WebSocket onError(IOnError onError) {
            synchronized (this) {
                this.onError = onError
            }
            return this
        }

        WebSocket connect() {
            synchronized (this) {
                if (webSocketClient == null) {
                    return this
                }
                webSocketClient = new WebSocketClient(new URI(url)) {
                    @Override
                    void onOpen(ServerHandshake serverHandshake) {
                        synchronized (WebSocket.this) {
                            onOpen?.onOpen(WebSocket.this, serverHandshake)
                        }
                    }

                    @Override
                    void onMessage(String message) {
                        synchronized (WebSocket.this) {
                            onMessage?.onMessage(WebSocket.this, message)
                        }
                    }

                    @Override
                    void onMessage(ByteBuffer bytes) {
                        synchronized (WebSocket.this) {
                            bytes.position(0)
                            def bb = new byte[bytes.remaining()]
                            bytes.get(bb)
                            onBytesMassage?.onMessage(WebSocket.this, bb)
                        }
                    }

                    @Override
                    void onClose(int code, String reason, boolean remote) {
                        synchronized (WebSocket.this) {
                            onClose?.onClose(WebSocket.this, code, reason, remote)
                            webSocketClient = null
                        }
                    }

                    @Override
                    void onError(Exception e) {
                        synchronized (WebSocket.this) {
                            onError?.onError(WebSocket.this, e)
                        }
                    }
                }
            }

            return this
        }

        void send(String text) {
            synchronized (this) {
                webSocketClient?.send(text)
            }
        }

        void send(byte[] bytes) {
            synchronized (this) {
                webSocketClient?.send(bytes)
            }
        }

        void ping() {
            synchronized (this) {
                webSocketClient?.sendPing()
            }
        }

        void close() {
            synchronized (this) {
                webSocketClient?.close()
            }
        }

        void close(int code) {
            synchronized (this) {
                webSocketClient?.close(code)
            }
        }

        void close(int code, String reason) {
            synchronized (this) {
                webSocketClient?.close(code, reason)
            }
        }
    }

}
