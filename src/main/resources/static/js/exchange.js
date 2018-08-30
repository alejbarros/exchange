   //Functions to works exchange 
	// post search-form
	$(document).ready(function () {
    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit();
       });

    });
    function fire_ajax_submit() {
    var codFrom = document.getElementById('currencyCodFrom').value;
    var codTo = document.getElementById('currencyCodTo').value;    
    var urlRest = '/convertModel/' +codFrom+"_"+codTo;
    var search = "";
    $.ajax({
         type: "POST",
        contentType: "application/json",
        url: urlRest ,
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = JSON.stringify(data, null, 4) ;
            var amount = document.getElementById('amount').value;
            var result = 0;
            if (amount != null && amount !=""){
                    result = amount * json;
            }else{
            	alert("Input amount please");
            }
            $('#resultCurrency').val(result);
            
            console.log("SUCCESS : ", data);           
        },
        error: function (e) {            
            console.log("ERROR : ", e);
        }
    });

	}
    
    // post search-form-countriesTop 
	$(document).ready(function () {
    $("#search-form-countriesTop").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submitTop();
       });

    });
    function fire_ajax_submitTop() {
    var codRateFrom = document.getElementById('countryRateFrom').value;
    var urlRestRate = '/home/' +codRateFrom;
    var search = "";
    $.ajax({
        type: "POST",
        url: urlRestRate ,
        data: JSON.stringify(search),
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (data) {
        	var result = $(data).find('#tableRates').html();
            $('#tableRates').html(result);        	
            console.log("SUCCESS : ", result);           
        },
        error: function (e) {            
            console.log("ERROR : ", e);
        }
    });

	}   
    // end post
   	
	function showCurrencyFrom(sel)
    {   
    	var str  = sel.options[sel.selectedIndex].value;
    	var fields = str.split('-'); 
    	var symbol = fields[2];
    	
    	if (symbol=="null"){ 
      		symbol=" ";
    	}
    	document.getElementById('currencyIdFrom').value=fields[1] + ' ' + symbol;
    	document.getElementById('currencyCodFrom').value=fields[0];
    }
    
    function showCurrencyTo(sel)
    {   
    	var str  = sel.options[sel.selectedIndex].value;
    	var fields = str.split('-'); 
    	var symbol = fields[2];
    	if (symbol=="null"){ 
      		symbol=" ";
    	}
    	document.getElementById('currencyIdTo').value=fields[1] + ' ' + symbol;
    	document.getElementById('currencyCodTo').value=fields[0];
    }
    
    function populateExchange()
    { 
    	var codFrom = document.getElementById('currencyCodFrom').value;
    	var codTo = document.getElementById('currencyCodTo').value;
    	document.getElementById('convertExchange').value=codFrom+"_"+codTo;
    }
	//init
	function validate(){
		var amount = document.getElementById('amount').value;
		var codFrom = document.getElementById('currencyCodFrom').value;
   		var codTo = document.getElementById('currencyCodTo').value;       	
		if( amount == "" ){
	        alert( "Please provide amount!" );
	        document.search-form.amount.focus() ;
	        return false;
    	}
   		 if( codFrom == "" ){
    	    alert( "Please provide currency From!" );
      	  return false;
    	}
    	if( codTo == "" ){
       	 alert( "Please provide currency To!" );
     	   return false;
    	}       
    	return true;        
	}
	function validateRates(){
		var countryRateFrom = document.getElementById('countryRateFrom').value;
		if(countryRateFrom == ""){
			alert("Please provide country!")
			return false;
		}
		return true;
	}
	//end    

// Tabbed Menu
function openMenu(evt, menuName) {
  var i, x, tablinks;
  x = document.getElementsByClassName("menu");
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < x.length; i++) {
     tablinks[i].className = tablinks[i].className.replace(" w3-dark-grey", "");
  }
  document.getElementById(menuName).style.display = "block";
  evt.currentTarget.firstElementChild.className += " w3-dark-grey";
}
document.getElementById("myLink").click();
