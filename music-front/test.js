function countA(str) {
    let count = 0;
    for (let i = 0; i < str.length; i++) {
      if (str[i] === 'A' || str[i] === 'a') {
        count++;
      }
    }
    return count;
  }
  
  // 测试
  console.log(countA("Hello, this is a test string with A and a.")); // 输出应该是3