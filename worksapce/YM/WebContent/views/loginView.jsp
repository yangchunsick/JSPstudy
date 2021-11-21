<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <style>
        *{
            margin: 0; padding: 0;
            box-sizing: border-box;
        }
        .wrap{
            width: 500px;
            margin: 150px auto;
        }
        .img_box{
            width: 300px; height: 300px;
            margin: 30px auto;
            background-image: url(./images/logo.png);
            background-size: 300px 300px;

        }
        .login_input{
            width: 400px; height: 50px;
            margin: 10px 50px;
            border: 1px solid gray;
            border-radius: 3px;
            font-size: 14px;
            cursor: pointer;
        }
        .login_input::placeholder{
            padding-left: 10px;
        }
        .login_input:focus{
            outline: 2px solid crimson;
        }

        .input_box button{
            width: 400px; height: 50px;
            margin: 10px 50px;
            border: 0;
            border-radius: 3px;
            background-color: gray;
            cursor: pointer;
        }
        .option_link{
            width: 180px;
            margin: 0 auto;
        }

        a{
            padding: 10px;
            text-decoration: none;
            text-align: center;
            color: gray;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script>
        $(document).ready(function(){
            
        });
    </script>
</head>
<body>
    <div class="wrap">
        <div class="img_box">
            <img src="../images/logo.png">
        </div>
        <div class="input_box">
        <form action="" method="post">
            <input class="login_input" type="text" name="id" id="id" placeholder="ID" autofocus>
            <input class="login_input" type="password" name="pw" id="pw" placeholder="PW">
            <button>LOGIN</button>
        </form>
        <div class="option_link">
            <a href="joinView.do">회원가입</a>
            <a href="#">계정 찾기</a>
        </div>
    </div>
    </div>
</body>
</html>