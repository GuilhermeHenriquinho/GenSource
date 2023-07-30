package gensource_realese.model.enumAndModel;

public enum Anotacao {
    ENTIDADE("Entity", "Indica que a classe � uma entidade, ou seja, ela representa uma tabela no banco de dados."),
    CHAVE_PRIMARIA("Id", "Define o atributo como a chave prim�ria da entidade."),

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    GERACAO_VALOR_CHAVE("GeneratedValue", "Especifica a estrat�gia de gera��o de valor para a chave prim�ria."),

//    @Column(name = "product_name", nullable = false, length = 100)
    COLUNA("Column", "Permite definir as propriedades de uma coluna do banco de dados para um atributo da entidade."),

//    @OneToMany(mappedBy = "order")
    UM_PARA_MUITOS("OneToMany", "Estabelece um relacionamento de um-para-muitos entre duas entidades."),

//    @ManyToOne
//    @JoinColumn(name = "order_id")
    MUITOS_PARA_UM("ManyToOne", "Estabelece um relacionamento de muitos-para-um entre duas entidades."),

//    @OneToOne
//    @JoinColumn(name = "user_id")
    UM_PARA_UM("OneToOne", "Estabelece um relacionamento de um-para-um entre duas entidades."),

//    @JoinColumn
    CHAVE_ESTRANGEIRA("JoinColumn", "Define a coluna que ser� usada como chave estrangeira na tabela relacionada."),

//    @Transient
    NAO_PERSISTIDO("Transient", "Indica que o atributo n�o ser� persistido no banco de dados, ou seja, ser� ignorado pelo JPA.");

    private final String titulo;
    private final String explicacao;

    Anotacao(String titulo, String explicacao) {
        this.titulo = titulo;
        this.explicacao = explicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getExplicacao() {
        return explicacao;
    }
}

