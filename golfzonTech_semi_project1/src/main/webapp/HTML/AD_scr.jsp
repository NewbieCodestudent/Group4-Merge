<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AD_srouce_page</title>
    <style>
        body {
            width: 1070px;
            height: 180px;
            background-color: powderblue;
            margin: 0px;
            padding: 0px;
        }
        img {
            width: 1070px;
            height: 200px;
            margin: 0px;
            padding: 0px;
        }
    </style>
</head>
<body>
    <a id="AD_link" href="" target="_parent">
        <img src="" alt="AD_view" id="AD_view">
        <script>
            const ad_number = Math.floor(Math.random() * 3);
            // console.log(ad_number);
            var img_view = "CSS/AD/AD_test_"+ad_number+".png";
            // console.log(img_view);
            document.getElementById("AD_view").src = img_view;
            var img_link = "www.naver.com";
            document.getElementById("AD_link").href = img_link;
        </script>
    </a>
    <img src="CSS/AD/AD_test_0.png" alt="AD">
    <img src="CSS/AD/AD_test_1.png" alt="AD">
    <img src="CSS/AD/AD_test_2.png" alt="AD">
</body>
</html>