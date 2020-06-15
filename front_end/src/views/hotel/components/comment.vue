<template>
    <div>
        <a-comment>
            <span slot="author" style="font-size: 15px">{{commentItem.userName}}</span>
            <a-avatar slot="avatar" src="@/assets/cover.jpeg"></a-avatar>
            <span slot="content">{{commentItem.content}}</span>
            <span slot="datetime">{{commentItem.commentDate.substr(0,10)}}</span>
            <template slot="actions">
      <span key="comment-basic-like">
        <a-tooltip title="Like">
          <a-icon type="like" :theme="flag === 'liked' ? 'filled' : 'outlined'" @click="like(commentItem.id)"/>
        </a-tooltip>
        <span style="padding-left: '8px';cursor: 'auto'">
          {{ commentItem.likeNum+likes }}
        </span>
      </span>
                <span key="comment-basic-dislike">
        <a-tooltip title="Dislike">
          <a-icon
                  type="dislike"
                  :theme="flag === 'disliked' ? 'filled' : 'outlined'"
                  @click="dislike(commentItem.id)"
          />
        </a-tooltip>
        <span style="padding-left: '8px';cursor: 'auto'">
          {{commentItem.dislikeNum+dislikes}}
        </span>
      </span>
            </template>
        </a-comment>
    </div>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";

    export default {
        name: "comment",
        data() {
            return {
                flag: null,
                likes: 0,
                dislikes: 0
            }
        },
        props: {
            commentItem: Object
        },
        computed: {
            ...mapGetters(['currentComment'])
        },
        mounted() {
            this.getCommentById(this.commentItem.id)
        },
        methods: {
            ...mapMutations([]),
            ...mapActions([
                'addCommentLikeNum',
                'addCommentDislikeNum',
                'subCommentLikeNum',
                'subCommentDislikeNum',
                'getCommentById'
            ]),
            like(id) {
                if (this.flag == null) {
                    this.addCommentLikeNum(id).then(() => {
                        this.getCommentById(id).then(() => {
                            this.flag = 'liked'
                            this.likes = 1
                        })
                    })
                }
                if (this.flag == 'liked') {
                    this.subCommentLikeNum(id).then(() => {
                        this.getCommentById(id).then(() => {
                            this.flag = 'null'
                            this.likes=0
                        })
                    })
                }
                if (this.flag == 'disliked') {
                    this.subCommentDislikeNum(id)
                    this.addCommentLikeNum(id).then(() => {
                        this.getCommentById(id).then(() => {
                            this.flag = 'liked'
                            this.dislikes=0
                            this.likes=1
                        })
                    })
                }

            },
            dislike(id) {
                if (this.flag == null) {
                    this.addCommentDislikeNum(id).then(() => {
                        this.getCommentById(id).then(() => {
                            this.flag = 'disliked'
                            this.dislikes=1
                        })
                    })
                }
                if (this.flag == 'disliked') {
                    this.subCommentDislikeNum(id).then(() => {
                        this.getCommentById(id).then(() => {
                            this.flag = null
                            this.dislikes=0
                        })
                    })
                }
                if (this.flag == 'liked') {
                    this.subCommentLikeNum(id)
                    this.addCommentDislikeNum(id).then(() => {
                        this.getCommentById(id).then(() => {
                            this.flag = 'disliked'
                            this.likes=0
                            this.dislikes=1
                        })
                    })
                }
            }
        }

    }
</script>

<style scoped>

</style>