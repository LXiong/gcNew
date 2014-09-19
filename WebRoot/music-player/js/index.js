$(document).ready(function(){
	var playlist = [{
		title:"采蘑菇的小姑娘",
		mp3:"../gcNew/music-player/music/mogu.wav"
	}];
	var cssSelector = {
		jPlayer: "#jquery_jplayer",
		cssSelectorAncestor: ".music-player"
	};
	var myPlaylist = new jPlayerPlaylist(cssSelector, playlist);
});