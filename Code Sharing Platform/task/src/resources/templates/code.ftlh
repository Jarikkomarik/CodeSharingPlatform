<html>
<head>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>

<title>Code</title>
</head>
<body>
<pre id="code_snippet"><code>${code.code}</code></pre>
<span id="load_date"> ${code.date} </span><br>
<#if code.isTimeIsLimited()>
<span id="time_restriction">Time: ${code.time} </span><br>
</#if>
<#if code.isViewsAreLimited()>
<span id="views_restriction">Views left: ${code.views} </span>
</#if>
</body>
</html>