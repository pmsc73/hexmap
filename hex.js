var clickCount = 0;
var assigned = false;

function readMouseMove(e) {
	currentCoords = document.getElementById('coords');
	currentCoords.innerHTML = getMouseCoords(e);
	div = document.getElementsByClassName('hex');
	for(var i=0; i < div.length; ++i) {
		div[i].innerHTML = "&#x2B22";
	}
	div = document.getElementsByClassName('hex-null');
	for(var i=0; i < div.length; ++i) {
		div[i].innerHTML = "&#x2B22";
	}
}
function readMouseClick(e) {
	clickCount=1;
	click_result = document.getElementById('clicks');
	click_result.innerHTML = getMouseCoords(e);
}
function getMouseCoords(e) {
	return "<pre>("+e.clientX+", " + e.clientY+ ")</pre>";
}
document.onmousemove = readMouseMove;
