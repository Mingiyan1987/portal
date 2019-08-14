function loadArticles() {
    //Формирование строки с данными, которые необхоимо передать на сервер в метод listAjax
    data = "pageCounter=" +pageCounter+"&"+"order="+order+"&"+"orderBy="+orderBy+"&"+"number="+number;
    $ajax({

        url:url, //Указание url-запроса
        type: 'GET', // Тип запроса
        data:data, // Данные, которые передаются на сервер
        cache:false,
        success: function (adsResponsive) {
            if (adsResponsive == 0) {
            } else {
                // Если ответ содержит данные. то они размещаются на странице,
                // а счетчик страниц (блоков) увеличивается на единицу
                renderingAds(adsResponsive["ads"]);
                pageCounter++;
            }
        },
    });
}

$(document).ready(function () {
    // Первая страница (блок) статей подгружается при загрузке страницы
    loadArticles();
    $(".btn_load").click(function () {
        // Остальные страницы подгружаются при нажатии на кнопку "Загрузить еще"
        loadArticles();
    })
});