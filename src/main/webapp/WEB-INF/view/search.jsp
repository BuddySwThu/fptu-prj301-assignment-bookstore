<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./resources/css/main.css">
    <title>Search Page</title>
</head>
<body>
    <h1>Search</h1>
    <div style="color: blueviolet">
        Welcome, ${sessionScope.USER.lastname}
        <a href="logout">Logout</a>
    </div>
    <br/>

    <div>
        Finding: <label><input type="text" name="searchValue"/></label>
        <input type="button" value="Search" onclick="search(this)"/>
    </div>
    <br/>

    <div id="result"></div>
    <script src="./resources/js/main.js"></script>
</body>
</html>
