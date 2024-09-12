<template>
    <div id="search-box">
        <input type="text" placeholder="搜索..." v-model="searchText" @keyup.enter="search()">
    </div>
</template>
<script>
export default {
    data() {
        return {
            searchText: ''
        }
    },
    methods: {
        search() {
            // TODO: Implement search logic
            console.log('Searching for:', this.searchText);
        }
    },
};
</script>
<style scoped>
#search-box {
    display: flex;
    background-color: #f5f5f5;
    border-radius: 25px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    max-width: 240px;
    position: fixed;
    top: 10px; /* 距离顶部的距离 */
    left: 50%; /* 水平居中 */
    transform: translateX(-50%); /* 向左移动自身宽度的一半 */
}

input[type="text"] {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    flex-grow: 1;
    border: none;
    outline: none;
    padding: 8px;
    border-radius: 25px;
    font-size: 20px;
}

</style>