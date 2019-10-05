function loadArticles(){
// Формирование строки с данными, которые необходимо передать на сервер в метод listAjax
    data="pageCounter="+pageCounter+"&"+"order="+order+"&"+"orderBy="+orderBy+"&"+"number="+number;

    $.ajax({
        url:url,          // Указание url-запроса
        type: 'GET',      // Тип запроса
        data:data,        // Данные, которые передаются на сервер
        cache:false,
        success: function(articlesResponsive){

            if(articlesResponsive==0){
            }else{

// Если ответ содержит данные, то они размещаются на странице,
// а счетчик страниц (блоков) увеличивается на единицу
                renderingArticles(articlesResponsive["articles"]);
                pageCounter++;
            }
        },
    });
}

function renderingArticles(articles) {
    articles.forEach(function (article) {
        var test = $(articleBody).find(".article__title").attr("href", contextPath + "/articles/" + article["id"].html(article["title"]))
            .end().find(".article__date").html(article["content"].substring(0, 110) + "....")
            .end().find("article__author").html(article["category"].name)
            .end().find(".more").attr("href", contextPath + "/articles/" + article["id"])
            .end().find(".edit").attr("href", contextPath + "/articles" + article["id"] + "/edit")
            .end().find(".delete").attr("href", contextPath + "/articles" + article[id] + "/delete")
            .end().appendTo("#templatemo_content");
    });
}

$(document).ready(function(){
// Первая страница (блок) статей подгружается при загрузке страницы
    loadArticles();
    $(".btn_load").click(function(){
// Остальные страницы подгружаются при нажатии на кнопку «Загрузить еще»
        loadArticles();
    })
});
