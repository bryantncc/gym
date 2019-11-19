"use strict";

$(document).on('ready', function() { 

	initParallax();
	initEvents();
	initMasonry();
	initMap();
	/* You can disable srollanimation by removing next function */
	initScrollAnimation();
	$(function() { $('.matchHeight').matchHeight(); });	
	$(".accordion").accordion();
	$(".tabs-ui").tabs();

	/* Lightbox plugin */
	$('.swipebox').swipebox();


	initSwiper();
});

$(window).on('scroll', function (event) {

	checkNavbar();
	checkCountUp();
	initProgressBar();
}).scroll();

/* Parallax fix on window resize */ 
$(window).on('resize', function(){

 	initParallax();
});

$(window).on('load', function(){

	initMasonry()
});

/* All keyboard and mouse events */
function initEvents() {

	/* Tabs block on main page */
	$('.block-tabs').on('click', '.item', function() {

		var parentDiv = $(this).closest(".block-tabs");

		parentDiv.find('.active').removeClass('active');
		parentDiv.find('.'+$(this).data('block')).addClass('active');
		$(this).addClass('active');

		return false;
	});

	/* Show/hide search form in navbar */
	var searchDiv = $('#search'), navbarDiv = $('#navbar');
	searchDiv.on('click', '.search-icon', function() {

		searchDiv.toggleClass('show');
		navbarDiv.toggleClass('show-search');
	});

	/* Up/down counter for input numeric box */
	$('.calc').on('click', '.arrow-up, .arrow-down', function() {

		var val = parseInt($(this).parent().find('input').val(), 10);
		if ($(this).hasClass('arrow-up')) val++; else val--;
		$(this).parent().find('input').val(val).change();
		return false;
	});

	/* 
		Perfect wieght formula user in calculator on main page. 
		* For male. (Height - 100 * 1.15 + Age * 0.05)
		* For female. (Height - 110 * 1.15 + Age * 0.05)

		You can use several calculators on a page
	*/
	$('.calculator').on('change keyup keydown keypress', 'input', function() {

		if ($('input[name="sex"]:checked').val() === 'man') {

			var val = parseInt((parseInt($('#height').val(), 10) - 100) * 1.15 + (parseInt($('#age').val(), 10) * 0.05), 10);
		}
			else {

			var val = parseInt((parseInt($('#height').val(), 10) - 110) * 1.15 + (parseInt($('#age').val(), 10) * 0.05), 10);
		}
		$('.calculator .result span').html(val);
	}).change();

	/* Scrolling to navbar from "go top" button in footer */
    $('footer').on('click', '.go-top', function() {

	    $('html, body').animate({ scrollTop: 0 }, 800);
	});
}

/* Masonry initialization */
function initParallax() {

	// Only for desktop
	if (/Mobi/.test(navigator.userAgent)) return false;

	$('.parallax').parallax("50%", 0.1);
}

/* Swiper slider initialization */
function initSwiper() {

	/* Slider for clients on main page */	
    var clientsSwiper = new Swiper('.clients-slider', {
		direction   : 'horizontal',

		speed		: 1000,
		nextButton	: '.arrow-right',
		prevButton	: '.arrow-left',
	
		autoplay    : 7000,
		autoplayDisableOnInteraction	: false,
    });

	/* Slider for inner pages */	
    var innerSwiper = new Swiper('.slider-inner', {
		direction   : 'horizontal',
        pagination: '.swiper-pagination',
        paginationClickable: true,		
		autoplay    : 4000,
		autoplayDisableOnInteraction	: false,        
    });    
}

/* Masonry initialization */
function initMasonry() {

	$('.masonry').masonry({
	  itemSelector: '.item',
	  columnWidth:  '.item'
	});		
}

/* Animated progress bar */
function initProgressBar() {

	var block = $('.progressItems');

    if (block.length) {

	    var scrollTop = block.offset().top - window.innerHeight;

	    if (!block.data('counted') && $(window).scrollTop() > scrollTop) {

	    	/* Initialized once */
			$('.progressBar').each(function(i, el) {
				progressBar(parseInt($(el).find('.value').html(), 10), $(el));
			});

	    	block.data('counted', 1);
	    }  
	}
}

function progressBar(percent, $element) {
    var progressBarWidth = percent * $element.width() / 100;
    $element.find('.bar div').animate({ width: progressBarWidth }, 1000);
}

/* Scroll animation used for landing page */
function initScrollAnimation() {

	window.sr = ScrollReveal();

	var scrollZoomIn = {
		duration: 400,
		scale    : 0.8,
		afterReveal: function (domEl) { $(domEl).css('transition', 'all .3s ease'); }
	};

	var scrollTextFade = {
		duration: 400,
		afterReveal: function (domEl) { $(domEl).css('transition', 'all .3s ease'); }
	}

	var scrollSliderFull = {
		duration: 1200,
		scale : 1,
		easing   : 'ease-in-out',
		distance : '0px',
		afterReveal: function (domEl) { $(domEl).css('transition', 'all .3s ease'); }
	}

	/* Every element initialized once */
	if ($('.slider-full').length) sr.reveal('.slider-full h3, .slider-full p, .slider-full a', scrollSliderFull, 400);

	if ($('.block-dark').length) sr.reveal('.block-dark h3, .block-dark p, .block-dark a', scrollTextFade, 200);
	if ($('.block-white').length) sr.reveal('.block-white h3, .block-white p, .block-white a', scrollTextFade, 20);
}

/* Starting countUp function on landing page */
function checkCountUp() {

	var countBlock = $('.skills');

    if (countBlock.length) {

	    var scrollTop = countBlock.offset().top - window.innerHeight;

	    if (!countBlock.data('counted') && $(window).scrollTop() > scrollTop) {

	    	/* Initialized once */
	    	for (var x = 1; x <= 4; x++) {

				var numAnim = new CountUp('countUp-' + x, 1, $('#countUp-' + x).html());
				numAnim.start();
	    	}

	    	countBlock.data('counted', 1);
	    }  
	}
}

/* Navbar is set darker on main page on scroll */
function checkNavbar() {

	var scroll = $(window).scrollTop(),
    	navBar = $('nav.navbar'),
	    slideDiv = $('.slider-full');

    if (scroll > slideDiv.height() - navBar.height()) navBar.addClass('dark'); else navBar.removeClass('dark');
}

/* Google maps init */
function initMap() {

	var mapEl = $('#map');
	if (mapEl.length) {

		var uluru = {lat: mapEl.data('lat'), lng: mapEl.data('lng')};
		var map = new google.maps.Map(document.getElementById('map'), {
		  zoom: 10,
		  center: uluru,
		  scrollwheel: false,
		  styles: mapStyles
		});

		var marker = new google.maps.Marker({
		  position: uluru,
		  map: map
		});
	}
}
