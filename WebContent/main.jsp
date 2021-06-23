<%@page import="com.alibaba.fastjson.JSON"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <script src="echarts.min.js"></script>
 <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
 <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
			#one{
				/*border: 1px solid black;*/
				width: 1000px;
				height:auto;
				background-color: ghostwhite;
				margin: auto;
			}
			#main{
			width:1000px;
			height:outo;
			background-color: ghostwhite;
			margin: auto;
			}
			.table
			{
			margin: auto;
			}
</style>
</head>
<body>
		<div id="one">
			请输入时间：<input type="text" name="date" id="date" />	  
			 <input type="submit" value="查询" onclick="search()">
		</div>
		<br/>
<div id="main" style="width: 100%;height:400px;overflow: auto;"></div>
<table class="table">
        <thead class="head">
            </thead>
        <tbody class="main"></tbody>
    </table>
<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
       
        // 指定图表的配置项和数据
        myChart.showLoading();
        function search() {
        	var date=$("#date").val();
           alert("查找成功");
            $.post(
                'Selvletsearch',
                {date:date},
                function(msg){
                	var json=JSON.parse(msg);
                    var size=json.length;
                    var names=[];
                    var nums=[];
                    for(i=0;i<size;i++){
                        names.push(json[i].name);
                        nums.push(json[i].num);
                        i++;
                    }
                    myChart.hideLoading();
                    var option = {
                            title: {
                                text: $("input[name=date]").val()+'确诊人数'
                            },
                            tooltip: {},
                            legend: {
                                data:['确诊人数']
                            },
                            xAxis: {
                                data: names
                            },
                            yAxis: {},
                            series: [{
                                name: '确诊人数',
                                type: 'bar',
                                data: nums
                            }]
                        };
                  myChart.setOption(option);
                  tr="<tr><th>省份</th><th>确诊人数</th><th>疑似人数</th><th>治愈人数</th><th>死亡人数</th><th>编码</th></tr>";
                  $('.head').append(tr);
                  for(i=0;i<size;i++)
                      $('.main').append("<tr></tr>");
                  $('.main tr').each(function(i){
                      $(this).append("<td>"+json[i].name+"</td>");
                      $(this).append("<td>"+json[i].num+"</td>");
                      $(this).append("<td>"+json[i].yisi+"</td>");
                      $(this).append("<td>"+json[i].cure+"</td>");
                      $(this).append("<td>"+json[i].dead+"</td>");
                      $(this).append("<td>"+json[i].code+"</td>");
                  })
           
                },
               //"json"
                );
            
        }
     
   </script>              
</body>
</html>