/**
 * socket.send 数据描述
 * - 使用Google的stun服务器
 *
 * event: 指令类型
 * data: 数据
 * name: 发送人
 * receiver: 接收人
 */
var iceServer = {
    "iceServers": [{
        "url": "stun:stun.l.google.com:19302"
    }, {
        "url": "turn:numb.viagenie.ca",
        "username": "webrtc@live.com",
        "credential": "muazkh"
    }]
};
//兼容浏览器的getUserMedia写法
var getUserMedia = (navigator.getUserMedia || navigator.mozGetUserMedia || navigator.webkitGetUserMedia || navigator.msGetUserMedia);
//兼容浏览器的PeerConnection写法
var PeerConnection = (window.PeerConnection ||
    window.webkitPeerConnection00 ||
    window.webkitRTCPeerConnection ||
    window.mozRTCPeerConnection);
/**
 * 信令websocket
 * @type {WebSocket}
 */
var socket;

function communication() {
    //创建PeerConnection实例
    var pc = new PeerConnection(iceServer);
    var video = document.getElementById('video');
    //如果检测到媒体流连接到本地，将其绑定到一个video标签上输出
    pc.ontrack = function async(event) {
        video.srcObject = event.streams[0]
    };
    var userName = document.getElementById('userName').value;
    var receiver = document.getElementById('receiver').value;
    /**
     * 信令websocket
     * @type {WebSocket}
     */
    socket = new WebSocket("ws://127.0.0.1:66/ws/" + userName + "?receiver=" + receiver);
    socket.close = function () {
        console.log("连接关闭")
    };
    //处理到来的信令
    socket.onmessage = function (event) {
        var json = JSON.parse(event.data);
        //如果是一个ICE的候选，则将其加入到PeerConnection中
        if (json.event === "__offer") {
            pc.setRemoteDescription(new RTCSessionDescription(json.data.sdp));
            pc.onicecandidate = function (event) {
                if (event.candidate !== null && event.candidate !== undefined && event.candidate !== '') {
                    socket.send(JSON.stringify({
                        "event": "__ice_candidate",
                        "data": {
                            "candidate": event.candidate
                        },
                        name: userName,
                        receiver: receiver
                    }));
                }
            };
            var agent = navigator.userAgent.toLowerCase();
            if (agent.indexOf("firefox") > 0) {
                pc.createAnswer().then(function (desc) {
                    pc.setLocalDescription(desc);
                    socket.send(JSON.stringify({
                        "event": "__answer",
                        "data": {
                            "sdp": desc
                        },
                        name: userName,
                        receiver: receiver
                    }));
                });
            } else {
                pc.createAnswer(function (desc) {
                    pc.setLocalDescription(desc);
                    socket.send(JSON.stringify({
                        "event": "__answer",
                        "data": {
                            "sdp": desc
                        },
                        name: userName,
                        receiver: receiver
                    }));
                }, function (error) {
                    alert(error);
                });
            }
        }
    };
}