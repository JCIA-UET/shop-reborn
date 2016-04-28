// View en grid et liste pour categories
$(document).ready(function() {
    $('#list').click(function(event){event.preventDefault();$('#categories-list .item').addClass('list-group-item');});
    $('#grid').click(function(event){event.preventDefault();$('#categories-list .item').removeClass('list-group-item');$('#categories-list .item').addClass('grid-group-item');});
});

// display full screen search box
$(function () {
    $('a[href="#search"]').on('click', function(event) {
        event.preventDefault();
        $('#search').addClass('open');
        $('#search > form > input[type="search"]').focus();
    });
    
    $('#search, #search button.close').on('click keyup', function(event) {
        if (event.target == this || event.target.className == 'close' || event.keyCode == 27) {
            $(this).removeClass('open');
        }
    });
});