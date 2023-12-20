<template>
    <div class="container">
        <div class="leftSide">
            <!--header-->
            <div class="header">
                <div class="userimg">
                    <img :src="this.$store.state.user.avatar" class="cover">
                </div>
                <ul class="nav_icons">
                    <!-- <li><img src="消息.png"></li> -->
                    <!-- <li><img src="更多.png"></li> -->
                </ul>
            </div>
            <!--search-->
            <div class="search_chat">
                <div>
                    <input type="text" placeholder="搜索...">
                    <!-- <img src="搜索.png"> -->
                </div>
            </div>
            <!--ChatList-->
            <div v-for="item, index in userList" class="chatlist" @click="changeUser(index)">
                <div class="block">
                    <div class="imgbx">
                        <img :src="avatars[index]" class="cover">
                    </div>
                    <div class="details">
                        <div class="listHead">
                            <h4>{{ item.userName }}</h4>
                            <!-- <p class="time">{{ messages[messages.length - 1].time }}</p> -->
                            {{ count[index] }}
                        </div>
                        <div class="message_p">
                            <!-- <p>{{ messages[messages.length - 1].content }}</p> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="rightSide">
            <div class="header">
                <div class="imgText">
                    <div class="userimg">
                        <img :src="avatars[userindex]" class="cover">
                    </div>
                    <h4>{{ names[userindex] }}<br><span>{{ userStatusText }}</span></h4>
                </div>
                <ul class="nav_icons">
                    <!-- <li><img src="消息.png"></li> -->
                    <!-- <li><img src="更多.png"></li> -->
                </ul>
            </div>
            <!--chatbox-->
            <div class="chatBox">
                <div v-for="message, index in messages" :key="index"
                    :class="{ my_message: message.isSentByUser, frnd_message: !message.isSentByUser }" class="message ">
                    <p>{{ message.text }}<br><span>{{ message.id }}</span> </p>
                </div>
            </div>

            <!--chat input-->
            <div class="chatbox_input">
                <div>
                    <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type a message..." />
                    <!-- <button @click="sendMessage">Send</button> -->
                </div>

            </div>
        </div>

    </div>
</template>
<script>
import { listUser } from "@/api/system/user";
import { formatDate } from "@/utils/index.js"
import { listHistory, readHistory } from "@/api/test/history"
let socket; // 声明 WebSocket 变量

export default {
    data() {
        return {
            newMessage: '',
            messages: [],
            tagetId: '',
            total: 0,
            // 用户表格数据
            userList: null,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                userName: undefined,
                phonenumber: undefined,
                status: undefined,
                deptId: undefined
            },
            avatars: [],
            userindex: 0,
            names: [],
            ids: [],
            messageHistory: [],
            count: [-1, -1, -1],
            onlineUsers: [],
            userStatusText: '离线',
            messageId: [[], [], []]
        };
    },
    methods: {
        sendMessage() {
            if (this.newMessage.trim() !== '') {
                this.messages.push({
                    id: formatDate(Date.now()).slice(11, 19),
                    text: this.newMessage,
                    isSentByUser: true, // 根据需要添加是否是用户发送的标志
                });
                // this.scrollToBottom()
                this.$nextTick(() => {
                    this.scrollToBottom();
                });
                const message = {
                    content: this.newMessage,
                    targetUserId: this.ids[this.userindex],
                    senderId: this.$store.state.user.id,

                };
                socket.send(JSON.stringify(message));
                this.newMessage = '';
            }
        },
        getList() {
            this.loading = true;
            listUser(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
                this.userList = response.rows;
                if (this.$store.state.user.id != 1) {
                    this.userList.length = 1
                    // console.log();
                    this.userList[0].userName = "客服"
                }
                this.userList.map(item => {
                    this.ids.push(item.userId)
                    this.names.push(item.userName)
                    this.avatars.push((item.avatar == "" || item.avatar == null) ? require("@/assets/images/profile.jpg") : process.env.VUE_APP_BASE_API + item.avatar)
                })
                this.total = response.total;
                this.loading = false;
            }
            );
        },
        changeUser(index) {
            this.userindex = index;
            if(this.messageId[index].length==0){
                this.updateHistory()
                for (let i = 0; i < this.onlineUsers.length; i++) {
                if (this.onlineUsers[i] == Number(this.ids.at(this.userindex))) {
                    this.userStatusText = '在线';
                    break
                }
                this.userStatusText = '离线'
            }
            }else{
            readHistory(this.messageId[index]).then(res => {
                this.updateHistory()
                for (let i = 0; i < this.onlineUsers.length; i++) {
                if (this.onlineUsers[i] == Number(this.ids.at(this.userindex))) {
                    this.userStatusText = '在线';
                    break
                }
                this.userStatusText = '离线'
            }
            })
            // this.userStatusText =this.onlineUsers.includes())? '在线' : '离线';
        }
        },
        scrollToBottom() {
            var chatBox = document.querySelector('.chatBox');
            chatBox.scrollTop = chatBox.scrollHeight - 100;
        },
        updateHistory() {
            listHistory().then(res => {
                this.count = [0, 0, 0]
                this.messageId = [[], [], []]
                this.messageHistory = res.rows;
                this.messages = []
                this.messageHistory.map(item => {
                    if (Number(item.receiver) == this.$store.state.user.id) {
                        if (item.read == "未读") {
                            this.ids.map((id, index) => {
                                if (id == item.sender) {
                                    this.count[index] = this.count[index] + 1
                                    this.messageId[index].push(item.id)
                                }
                            })
                        }
                    }
                    if (Number(item.receiver) == this.$store.state.user.id && Number(item.sender) == this.ids.at(this.userindex)) {
                        this.messages.push({
                            id: item.time.slice(11, 19),
                            text: item.content,
                            isSentByUser: false, // 根据需要添加是否是用户发送的标志
                        });
                    }
                    if (Number(item.sender) == this.$store.state.user.id && Number(item.receiver) == this.ids.at(this.userindex)) {
                        this.messages.push({
                            id: item.time.slice(11, 19),
                            text: item.content,
                            isSentByUser: true, // 根据需要添加是否是用户发送的标志
                        });
                    }
                })
                console.log(this.messageId);

            })
            this.$nextTick(() => {
                this.scrollToBottom();
            });

        }
    },
    beforeDestroy() {
        // 在组件销毁前关闭 WebSocket 连接
        if (socket && socket.readyState === WebSocket.OPEN) {
            socket.close();
        }
    },
    created() {
        this.getList()
        this.updateHistory()
        const userId = this.$store.state.user.id; // 替换为实际的用户ID
        socket = new WebSocket(`ws://localhost:8080/chat/${userId}`);
        // 监听 WebSocket 事件
        socket.addEventListener('open', (event) => {
            console.log(event);
            // 在连接成功后，可以执行其他操作
        });

        socket.addEventListener('close', (event) => {
            if (event.code === 1000) {
                console.log('WebSocket connection closed cleanly');
            } else {
                console.error('WebSocket connection closed unexpectedly');
            }
            // 在连接关闭时，可以执行清理操作或尝试重新连接
        });

        socket.addEventListener('error', (error) => {
            console.error('WebSocket error:', error);
            // 处理 WebSocket 错误
        });
        socket.addEventListener('message', (event) => {
            const message = JSON.parse(event.data); // 假设消息是 JSON 格式
            console.log(message);
            if ("onlineUsers" in message) {
                this.onlineUsers = message.onlineUsers
            } else {

                this.ids.map((id, index) => {
                    if (id == message.senderId) {
                        this.count[index] = this.count[index] + 1
                        // this.messageId[index].push(item.id)
                        console.log(id);
                    }
                })
                if (message.senderId == this.ids[this.userindex]) {
                    this.messages.push({ id: formatDate(Date.now()).slice(11, 19), text: message.content, isSentByUser: false });

                }
                this.$nextTick(() => {
                    this.scrollToBottom();
                });
            }
        });

    },
};
</script>
  
<style scoped>
@import url('https://fonts.googleapis.com css2?family-Open+Sans:wght@300;400;00;600;700&display=swap');

.container {
    position: relative;
    width: 100%;
    max-width: 100%;
    height: calc(100vh - 100px);
    background: #fff;
    box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.06), 0 2px 5px 0 rgba(0, 0, 0, 0.06);
    display: flex;
}

.container .leftSide {
    position: relative;
    flex: 30%;
    background: #fff;
    border-right: 1px solid rgba(0, 0, 0, 0.2);
}

.rightSide {
    position: relative;
    flex: 70%;
    background: #fbfbfa;
    height: 100%;
}

.container .rightSide::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    /* background:url(img6-bg.jpg) ; */
    opacity: 0.06;
}

.header {
    position: relative;
    width: 100%;
    height: 60px;
    background: #ededed;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 15px;
}

.userimg {
    position: relative;
    width: 40px;
    height: 40px;
    overflow: hidden;
    border-radius: 50%;
    cursor: pointer;
}

.cover {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.nav_icons {
    display: flex;
}

.nav_icons li {
    display: flex;
    list-style: none;
    cursor: pointer;
    color: #51585c;
    font-size: 1.5em;
    margin-left: 22px;
}

.search_chat {
    position: relative;
    width: 100%;
    height: 50px;
    background: #f6f6f6;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 15px;
}

.search_chat div {
    width: 100%;
}

.search_chat div input {
    width: 100%;
    outline: none;
    border: none;
    background: #fff;
    padding: 6px;
    height: 38px;
    border-radius: 30px;
    font-size: 14px;
    padding-left: 40px;
}

.search_chat div input::placeholder {
    color: #bbb;
}

.search_chat div img {
    position: absolute;
    left: 30px;
    top: 14px;
    font-size: 1.5em;
}

.chatlist {
    position: relative;
    height: calc(100%-110px);
    overflow-y: auto;
}

.chatlist .block {
    position: relative;
    width: 100%;
    display: flex;
    align-items: center;
    padding: 15px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.06);
}

.chatlist .block.active {
    background: #ebebeb;
}

.chatlist .block:hover {
    background: #f5f5f5;
}

.chatlist .block .imgbx {
    position: relative;
    min-width: 45px;
    height: 45px;
    overflow: hidden;
    border-radius: 50%;
    margin-right: 10px;
}

.chatlist .block .details {
    position: relative;
    width: 100%;
}

.chatlist .block .details .listHead {
    display: flex;
    justify-content: space-between;
    margin-bottom: 5px;
}

.chatlist .block .details .listHead h4 {
    font-size: 1.1em;
    font-weight: 600;
    color: #111;
}

.chatlist .block .details .listHead .time {
    font-size: 0.75em;
    color: #aaa;
}

.chatlist .block .details .listHead .time {
    color: #111;
}

.message_p {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.message_p p {
    color: #aaa;
    display: -webkit-box;
    -webkit-line-break: 1;
    font-size: 0.9em;
    overflow: hidden;
    text-overflow: ellipsis;
}

.message_p b {
    background-color: red;
    color: #fff;
    min-width: 20px;
    height: 20px;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 0.75em;
}

.imgText {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
}

.imgText h4 {
    font-weight: 500;
    line-height: 1.2em;
    margin-left: 15px;
}

.imgText h4 span {
    font-size: 0.8em;
    color: #555;
}

.chatBox {
    position: relative;
    width: 100%;
    height: 85%;
    padding: 50px;
    overflow-y: scroll;
}

.message {
    position: relative;
    display: flex;
    width: 100%;
    margin: 5px 0;
}

.message p {
    position: relative;
    right: 0;
    text-align: right;
    max-width: 65%;
    padding: 12px;
    background: #dcf8c6;
    border-radius: 10px;
    font-size: 0.9em;
}

.message p::before {
    content: '';
    position: absolute;
    top: 0;
    right: -12px;
    width: 20px;
    height: 20px;
    background: linear-gradient(135deg, #dcf8c6 0%, #dcf8c6 50%, transparent 50%, transparent);
}

.message p span {
    display: block;
    margin-top: 5px;
    font-size: 0.85em;
    opacity: 0.5;
}

.my_message {
    justify-content: flex-end;
}

.frnd_message {
    justify-content: flex-start;
}

.frnd_message p {
    background: #817ee2;
    text-align: left;
}

.message.frnd_message p::before {
    content: '';
    position: absolute;
    top: 0;
    left: -12px;
    width: 20px;
    height: 20px;
    background: linear-gradient(225deg, #817ee2 0%, #817ee2 50%, transparent 50%, transparent);
}

.chatbox_input {
    width: 100%;
    height: 70px;
    background: #f0f0f0;
    padding: 15px;
    justify-content: space-between;
    align-items: center;
}

.chatbox_input input {
    position: relative;
    width: 90%;

    margin: 0 20px;
    padding: 10px 20px;
    border: none;
    outline: none;
    border-radius: 30px;
    font-size: 1em;
}
</style>
  