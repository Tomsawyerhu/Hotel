<template>
    <div>
        <a-comment>
           <span slot="author" style="font-size: 15px">{{commentItem.userName}}</span>
            <a-avatar slot="avatar" src="@/assets/cover.jpeg"></a-avatar>
            <span slot="content">{{commentItem.content}}</span>
            <span slot="datetime">{{commentItem.commentDate}}</span>
        </a-comment>
    </div>
</template>

<script>
    export default {
        name: "comment",
        props:{
          commentItem: Object
        },

    }
</script>

<style scoped>

</style>