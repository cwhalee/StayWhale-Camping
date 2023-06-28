$('.wrap_side ul > li').click(function(e) {
  e.preventDefault();
  $('.wrap_side ul > li').removeClass('selected');
  $(this).addClass('selected');
  showContent($(this).attr('data-content-id'));
});
