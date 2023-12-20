<template>
  <div class="app-container bank-backend">
    <h1 class="welcome-message">欢迎使用银行后台系统</h1>
	<h1 class="welcome-message1">欢迎使用银行后台系统</h1>
	<h1 class="welcome-message2">欢迎使用银行后台系统</h1>
    <el-row :gutter="20">
      <!-- Rest of the code remains unchanged -->
    </el-row>
  </div>
</template>

<script>
export default {
  name: "BankBackendPage",
  // Add your data, methods, and other logic as needed
};
</script>

<style scoped lang="scss">
.bank-backend {
  // Add custom styling for the banking backend page
}

.welcome-message {
  font-size: 3em; /* Set the font size to 3 times larger */
  font-weight: bold;
  margin-bottom: 20px;
  text-align: center;
  color: #345678; /* Choose a suitable color */
}
.welcome-message1 {
  font-size: 5em; /* Set the font size to 3 times larger */
  font-weight: bold;
  margin-bottom: 20px;
  text-align: center;
  color: #345678; /* Choose a suitable color */
}
.welcome-message2 {
  font-size: 6em; /* Set the font size to 3 times larger */
  font-weight: bold;
  margin-bottom: 20px;
  text-align: center;
  color: #345678; /* Choose a suitable color */
}

// Add additional styling for other sections as needed
</style>
