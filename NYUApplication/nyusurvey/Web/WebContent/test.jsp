<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><!-- CDN hosted by Cachefly -->
<script src="tinymce/tinymce.min.js"></script>
<script language="JavaScript">
function doGetValue() {
	//alert("Value = "+tinymce.Editor.getContent());
	document.formName.submit();
}
</script>
<script>tinymce.init({
    selector: "textarea",
    theme: "modern",
    plugins: [
        "advlist autolink lists link image charmap print preview hr anchor pagebreak",
        "searchreplace wordcount visualblocks visualchars code fullscreen",
        "insertdatetime media nonbreaking save table contextmenu directionality",
        "emoticons template paste textcolor colorpicker textpattern imagetools legacyoutput"
    ],
    toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
    toolbar2: "print preview media | forecolor backcolor emoticons",
    image_advtab: true,
    templates: [
        {title: 'Test template 1', content: 'Test 1'},
        {title: 'Test template 2', content: 'Test 2'}
    ]
});
</script>
</head>
<body>
	   <form action="TinyMCEReader" name=formName method="post">
	   	<textarea name="tinyTextArea" text="this.setValue(${requestScope.textToShow})"></textarea>
	   	<button type="button" onclick="javascript:doGetValue()">Get Value</button>
	   </form>
        
</body>
</html>