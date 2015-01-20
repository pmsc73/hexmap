var clickCount = 0;
var assigned = false;

function readMouseMove(e) {
	document.getElementsByTagName('body')[0].style="background-color:#222";
	tiles = document.getElementsByClassName('hex');
	for(var i=0; i < tiles.length; ++i) {
		var t = tiles[i];
		var xpos = t.getAttribute('data-x')*24;
		if((parseInt(t.getAttribute('data-y'))%2)==0) {
			xpos+=12;
		}
		t.style="color:#D44;font-size:32px;position:fixed;top:"+(64+t.getAttribute('data-y')*24)+";left:"+xpos+";";
	}
	labels = document.getElementsByClassName('label');
	for(var j=0; j < labels.length; ++j) {
		var l = labels[j];
		var xpos = 10+parseInt(l.getAttribute('data-x'))*24;
		if((parseInt(l.getAttribute('data-y'))%2)==0) {
			xpos+=12;
		}
		l.style="font-family:\"Courier New\",Courier,monospace;font-size:8px;color:#000;position:fixed;top:"+(14+64+l.getAttribute('data-y')*24)+";left:"+xpos+";";
	}
}
function readMouseClick(e) {
	clickCount=1;
	click_result = document.getElementById('clicks');
	click_result.innerHTML += getMouseCoords(e);
}
function getMouseCoords(e) {
	return "<pre>("+e.clientX+", " + e.clientY+ ")</pre>";
}
function initialize(e) {
	tiles = document.getElementsByClassName('hex');
	for(var i=0; i < tiles.length; ++i) {
		var t = tiles[i];
		t.style+="position:fixed;top:"+t.getAttribute('data-y')*12+";left:"+t.getAttribute('data-x')*12+";";
	}
}
document.onmousemove = readMouseMove;
document.onclick = readMouseClick;
document.onload = initialize;
