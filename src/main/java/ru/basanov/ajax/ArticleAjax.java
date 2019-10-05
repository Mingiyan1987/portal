package ru.basanov.ajax;

import lombok.Getter;
import lombok.Setter;
import ru.basanov.model.Article;


import java.util.List;

@Getter
@Setter
public class ArticleAjax {

    private List<Article> articles;
}
