$(document).ready(function(){
    var jVal = {
        'login': function () {
            $('body').append('<div id="loginInfo" class="info"></div>');
            var loginInfo = $('#loginInfo');
            var ele = $('#input-login');
            var pos = ele.offset();

            loginInfo.css({
                top: pos.top + 3,
                left: pos.left + ele.width() + 30
            });

            var patt = /^[\w_]{3,16}$/i;

            if (!patt.test(ele.val())) {
                jVal.errors = true;
                loginInfo.removeClass('correct').addClass('error').html('incorrect').show();
                ele.removeClass('normal').addClass('wrong');
            } else {
                loginInfo.removeClass('error').addClass('correct').html('ok').show();
                ele.removeClass('wrong').addClass('normal');
            }
        },
        'name': function () {
            $('body').append('<div id="nameInfo" class="info"></div>');
            var nameInfo = $('#nameInfo');
            var ele = $('#input-name');
            var pos = ele.offset();

            nameInfo.css({
                top: pos.top + 3,
                left: pos.left + ele.width() + 30
            });

            var patt = /^[\w\u0430-\u044f]{3,20}$/ig;

            if (!patt.test(ele.val())) {
                jVal.errors = true;
                nameInfo.removeClass('correct').addClass('error').html('incorrect').show();
                ele.removeClass('normal').addClass('wrong');
            } else {
                nameInfo.removeClass('error').addClass('correct').html('ok').show();
                ele.removeClass('wrong').addClass('normal');
            }
        },
        'lastName': function () {
            $('body').append('<div id="lastNameInfo" class="info"></div>');
            var lastNameInfo = $('#lastNameInfo');
            var ele = $('#input-last-name');
            var pos = ele.offset();

            lastNameInfo.css({
                top: pos.top + 3,
                left: pos.left + ele.width() + 30
            });

            var patt = /^[\w\u0430-\u044f]{3,20}$/ig;

            if (!patt.test(ele.val())) {
                jVal.errors = true;
                lastNameInfo.removeClass('correct').addClass('error').html('incorrect').show();
                ele.removeClass('normal').addClass('wrong');
            } else {
                lastNameInfo.removeClass('error').addClass('correct').html('ok').show();
                ele.removeClass('wrong').addClass('normal');
            }
        },
        'phone': function () {
            $('body').append('<div id="phoneInfo" class="info"></div>');
            var phoneInfo = $('#phoneInfo');
            var ele = $('#input-phone');
            var pos = ele.offset();

            phoneInfo.css({
                top: pos.top + 3,
                left: pos.left + ele.width() + 30
            });

            var patt = /^\+?\d{7,20}$/ig;

            if (!patt.test(ele.val())) {
                jVal.errors = true;
                phoneInfo.removeClass('correct').addClass('error').html('incorrect').show();
                ele.removeClass('normal').addClass('wrong');
            } else {
                phoneInfo.removeClass('error').addClass('correct').html('ok').show();
                ele.removeClass('wrong').addClass('normal');
            }
        },
        'mail': function () {
            $('body').append('<div id="mailInfo" class="info"></div>');
            var mailInfo = $('#mailInfo');
            var ele = $('#input-mail');
            var pos = ele.offset();

            mailInfo.css({
                top: pos.top + 3,
                left: pos.left + ele.width() + 30
            });

            var patt = /^[\w]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$/ig;

            if (!patt.test(ele.val())) {
                jVal.errors = true;
                mailInfo.removeClass('correct').addClass('error').html('incorrect').show();
                ele.removeClass('normal').addClass('wrong');
            } else {
                mailInfo.removeClass('error').addClass('correct').html('ok').show();
                ele.removeClass('wrong').addClass('normal');
            }
        },
        'password': function () {
            $('body').append('<div id="passwordInfo" class="info"></div>');
            var passwordInfo = $('#passwordInfo');
            var ele = $('#input-password');
            var pos = ele.offset();

            passwordInfo.css({
                top: pos.top + 3,
                left: pos.left + ele.width() + 30
            });

            var patt = /^[\w]{5,18}$/ig;

            if (!patt.test(ele.val())) {
                jVal.errors = true;
                passwordInfo.removeClass('correct').addClass('error').html('incorrect').show();
                ele.removeClass('normal').addClass('wrong');
            } else {
                passwordInfo.removeClass('error').addClass('correct').html('ok').show();
                ele.removeClass('wrong').addClass('normal');
            }
        },
        'repeatPassword': function () {
            $('body').append('<div id="repeatPasswordInfo" class="info"></div>');
            var repeatPasswordInfo = $('#repeatPasswordInfo');
            var ele = $('#input-repeat-password');
            var pos = ele.offset();

            repeatPasswordInfo.css({
                top: pos.top + 3,
                left: pos.left + ele.width() + 30
            });

            var patt = /^[\w]{5,18}$/ig;

            if (!patt.test(ele.val())) {
                jVal.errors = true;
                repeatPasswordInfo.removeClass('correct').addClass('error').html('incorrect').show();
                ele.removeClass('normal').addClass('wrong');
            } else {
                if ($('#input-password').val() === ele.val()) {
                    repeatPasswordInfo.removeClass('error').addClass('correct').html('ok').show();
                    ele.removeClass('wrong').addClass('normal');
                } else {
                    jVal.errors = true;
                    repeatPasswordInfo.removeClass('correct').addClass('error').html('don\'t match').show();
                    ele.removeClass('normal').addClass('wrong');
                }
            }
        }
    };


    $('#input-login').change(jVal.login);
    $('#input-name').change(jVal.name);
    $('#input-last-name').change(jVal.lastName);
    $('#input-phone').change(jVal.phone);
    $('#input-mail').change(jVal.mail);
    $('#input-password').change(jVal.password);
    $('#input-repeat-password').change(jVal.repeatPassword);

});

$(document).ready(function() {
    $('.form-group input').on('keyup', function() {
        let empty = false;

        $('.form-group input').each(function() {
            empty = $(this).val().length == 0;
        });

        if (empty)
            $('#submit').attr('disabled', 'disabled');
        else
            $('#submit').attr('disabled', false);
    });

    $('.form-group input').on('focusout', function() {
        let empty = false;

        $('.form-group input').each(function() {
            empty = $(this).val().length == 0;
        });

        if (empty)
            $('#submit').attr('disabled', 'disabled');
        else
            $('#submit').attr('disabled', false);
    });
});