


function enableTenantID(){
	
		if($('#tenantCheckbox').is(':checked'))
		{$('#TENANT_ID').removeAttr('disabled');
		}
		else
		{	$('#TENANT_ID').attr('value','');
			$('#TENANT_ID').attr('disabled','disabled');
		}	
}





function setHiddenValue(HiddenParameterName,formId)
{
var temp=$('#'+formId).serialize();
$('input[name='+ HiddenParameterName+']').val(temp);

}



function addBTLcallToServer()
{	codXMLform=document.getElementById("CODXML");
	codXMLform.action="/CODGenerator/addNewBTL.action";
	document.getElementsByName("model.btlDataList[0].btlCount").value="0";
	codXMLform.submit();
	
}

function submitCOD(){
	codXMLform=document.getElementById("CODXML");
	codXMLform.action="/CODGenerator/generateNewXML.action";
	codXMLform.submit();

}

function createSelectBox(){
	
	btlArray=document.getElementsByClassName("BTL");
	BTLselectbox=document.getElementById("newBTL");
	
	
	for ( var i=0; i < btlArray.length; i++) {
			var option = document.createElement("option"); 	
			option.value =btlArray[i].value;
		 	option.text = btlArray[i].value;
		 	BTLselectbox.appendChild(option);
	}
	
	
}