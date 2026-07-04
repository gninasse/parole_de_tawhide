package com.example.data

data class Chapter(
    val id: Int,
    val title: String,
    val subtitle: String = "",
    val paragraphs: List<String>
)

object BookData {
    val title = "L'Explication de la Parole d'Unicité"
    val author = "Dr. Rabî' Ibn Hâdî Al-Madkhalî"
    val subtitle = "Tafsir Kalimat ut-Tawheed"

    val chapters = listOf(
        Chapter(
            id = 1,
            title = "Introduction & Sermon de préambule",
            subtitle = "Khutbat al-Hâjah",
            paragraphs = listOf(
                "Au nom d'Allah le Tout-Miséricordieux le Très-Miséricordieux.",
                "La louange revient de droit à Allah. Nous Le louangeons, demandons Son aide et Son pardon. Nous cherchons refuge auprès d'Allah contre les maux de nos âmes et contre nos mauvaises actions. Celui qu'Allah guide, nul ne saurait l'égarer ; et celui qu'Il égare n'aura aucun guide. J'atteste que nulle divinité n'est digne d'adoration sauf Allah, Seul sans aucun associé ; et j'atteste que notre prophète Mouhammad est Son serviteur et messager.",
                "{Ô les croyants ! Craignez Allah comme Il doit être craint. Et ne mourez qu'en pleine soumission (pour Lui).} [Âl `Imrân : 102].",
                "{Ô hommes ! Craignez votre Seigneur qui vous a créés d'un seul être, a créé de celui-ci son épouse, et de ces deux-là a fait répandre (sur terre) beaucoup d'hommes et de femmes. Craignez Allah au nom duquel vous vous implorez les uns les autres, et craignez de rompre les liens du sang. Certes Allah vous observe parfaitement.} [An-Nisâ' : 1].",
                "{Ô vous qui croyez ! Craignez Allah et parlez avec droiture, afin qu'Il améliore vos actions et vous pardonne vos péchés. Quiconque obéit à Allah et à Son messager obtient certes une grande réussite.} [Al-Ahzâb : 70-71].",
                "Certes la parole la plus véridique est celle d'Allah ; la meilleure guidée est celle de Mouhammad (que la prière et le salut d'Allah soient sur lui) ; les pires choses sont celles qui sont inventées [en religion], toute invention en religion d'Allah est une innovation ; toute innovation [en religion] est un égarement ; et tout égarement est au Feu."
            )
        ),
        Chapter(
            id = 2,
            title = "L'attachement au Livre et à la Sounnah",
            subtitle = "Se cramponner à la guidée",
            paragraphs = listOf(
                "Ceci dit,",
                "L'attachement au Livre et à la sounnah, dans la croyance et la méthodologie est une affaire nécessaire pour tout musulman. Nous devons nous cramponner au livre d'Allah et à la sounnah du Messager d'Allah (que la prière et le salut d'Allah soient sur lui) où il reçut des paroles brèves contenant des sens immenses."
            )
        ),
        Chapter(
            id = 3,
            title = "Le célèbre Hadith de Jibrîl",
            subtitle = "Une synthèse de la religion",
            paragraphs = listOf(
                "Parmi ses paroles laconiques et riches et qui englobent toute la religion, il y a le célèbre hadith de Jibrîl, comme cela fut narré par le calife bien-guidé `Oumar ibn al-Khattâb (qu'Allah l'agrée).",
                "Il dit : « Nous étions assis chez le Messager d'Allah, lorsqu'était venu vers nous un homme ayant des habits très blancs et une chevelure très noire. On ne remarquait pas sur lui l'effet d'un voyage et personne parmi nous ne le connaissait. Il posa ses genoux près de ceux (du Messager), ses paumes sur ses (propres) cuisses et il dit (au Prophète) : « Informe-moi sur l’islam ».",
                "Il répondit : « L'islam est que tu attestes que nulle divinité n'est digne d'adoration sauf Allah et que Mouhammad est le Messager d'Allah, que tu établisses la prière, que tu t'acquittes de l'aumône obligatoire, que tu jeûnes le ramadan et que tu entreprennes le pèlerinage si tu en es capable ».",
                "Il dit : « Tu as dit vrai ». Il (`Oumar) dit : « Nous étions étonnés du fait qu'il l'interrogeait et confirmait son propos » ; car d'habitude, l'ignorant quand il questionne sur des choses qu'il méconnait, il ne dit pas à celui qui lui répond : « Tu as dit vrai » ! Mais plutôt, celui qui le dit est bien quelqu'un qui connaissait cette réponse.",
                "« Nous étions étonnés du fait qu'il l'interrogeait et le confirmait. Il (Jibrîl) dit : « Informe-moi donc sur la foi ».",
                "Il (le Prophète) dit : « La foi est que tu croies en Allah, en Ses anges, en Ses livres, en Ses messagers, au jour dernier et au destin bon et mauvais ».",
                "Il dit : « Informe-moi sur la bienfaisance ». Il dit : « La bienfaisance est que tu adores Allah comme si tu Le voyais. Et, si tu ne Le vois pas, Lui, Il te voit ».",
                "Il dit : « Informe-moi sur l'Heure ». Il dit : « Celui que l'on questionne à son sujet n'en sait pas davantage que le questionneur ».",
                "Il dit : « Informe-moi sur ses signes ». Il dit : « Quand la servante donnera naissance à sa maitresse et que tu verras les va-nu-pieds, les dénudés, les nécessiteux, les pâtres du bétail rivaliser dans les hauts bâtiments ».",
                "Puis, il (Jibrîl) partit et il (le Prophète) demeura un long moment. Ensuite, il dit : « Savez-vous qui est le questionneur ? » Ils ont dit : Allah et Son Messager sont plus savants. Il dit : « C'était Jibrîl qui vint vous apprendre votre religion » ».",
                "Jibrîl était venu dans cet état étrange que `Oumar avait narré, sur l'ordre d'Allah, parce qu'Allah dit : {« Nous ne descendons que sur ordre de ton Seigneur.} [Maryam : 64]. Jibrîl ne vient que sur l'ordre d'Allah. Un jour, il avait tardé à venir chez le Prophète. Il lui en demanda la raison. Alors, Allah fit descendre : {« Nous ne descendons que sur ordre de ton Seigneur. À Lui tout ce qui est devant nous, tout ce qui est derrière nous et tout ce qui est entre les deux. Ton Seigneur n'oublie rien} [Maryam : 64]."
            )
        ),
        Chapter(
            id = 4,
            title = "Les Piliers de l'Islam et de la Foi",
            subtitle = "Les fondations de la croyance",
            paragraphs = listOf(
                "Le Prophète dit : « Il vint vous apprendre votre religion », parce que ce sont là des questions grandioses qui renferment les piliers de l'islam, ceux de la foi et le troisième degré qui est la bienfaisance. Les textes du Coran et de la sounnah gravitent autour de ces fondements et ils ajoutent des compléments dans les croyances, les transactions, etc., la mention des piliers de la foi et ceux de l'islam, qui sont indispensables.",
                "Ils doivent tous se réunir chez le musulman. S'il en perd un parmi eux, il ne serait ni musulman ni croyant. Il (le Prophète) a rendu les piliers de l'islam au nombre de cinq, comme dans le hadith de `Abd Allah ibn `Oumar (qu'Allah les agrée) : « L'islam est construit sur cinq : l'attestation que nulle divinité n'est digne d'adoration en dehors d'Allah et que Mouhammad est le Messager d'Allah, l'établissement de la prière, l'acquittement de l'aumône obligatoire, le jeûne du ramadan et le pèlerinage vers la maison (la ka`ba) pour celui qui en est capable »."
            )
        ),
        Chapter(
            id = 5,
            title = "L'Attestation d'Unicité",
            subtitle = "Le fondement de toute chose",
            paragraphs = listOf(
                "L'attestation « Nulle divinité n'est digne d'adoration en dehors d'Allah » est la base de la religion entière. L'individu ne rentre en islam que par son moyen. S'il commet ce qui l'annule, il quitte l'islam. Son sens est que personne ne mérite l'adoration de droit, sauf Allah.",
                "Tu dis « Je témoigne que nul ne mérite l'adoration en dehors d’Allah » c'est-à-dire que tu attestes qu'Allah Seul mérite d'être adoré et que l'adoration de tout autre que Lui est nulle : les prophètes, les anges, les vertueux, les arbres, les pierres, le soleil et la lune ; toutes ces choses ont été adorées en dehors d'Allah, mais leur adoration est nulle.",
                "Le croyant témoigne qu'Allah Seul mérite l'adoration ; personne d'autre que Lui n'est associé dans un atome d'elle. Si tu diriges une partie de cette adoration – qu'il faut vouer à Allah seul exclusivement – à autre que Lui, tu auras donc associé – qu'Allah nous préserve –."
            )
        ),
        Chapter(
            id = 6,
            title = "Unicité de Seigneurie et de Divinité",
            subtitle = "Comprendre la différence",
            paragraphs = listOf(
                "Nous devons savoir quel est le sens de l'adoration et celui de l'attestation [Nulle divinité n'est digne d'adoration en dehors d'Allah], car beaucoup de gens ne le savent pas. Ils disent : son sens est qu'il n'y a pas de créateur, ni de pourvoyeur, ni qui donne la vie, ni qui donne la mort, ni qui amène le mal ou le bien... sauf Allah !",
                "Ceci est la foi en la seigneurie, que [la tribu] Qouraysh avait ainsi que les communautés l'ayant précédée et qui avaient démenti les prophètes. Ils croient qu'Allah est Le Seigneur du ciel et de la terre, qu'Il est Le Créateur de cet univers, son gestionnaire et organisateur... Mais, ils n'avouent pas que Lui seul mérite l'adoration !",
                "Et, parmi les preuves sur la différence entre l'unicité de seigneurie et celle de divinité – qui sont toutes vraies, par Allah –, c'est que la seigneurie a un sens propre ainsi que des attributs particuliers ; et la divinité a un sens propre. Elles sont toutes deux indispensables. Les mécréants distinguaient entre l'unicité de seigneurie et celle de divinité.",
                "Ils reconnaissaient (la première), comme Allah avait informé à leur sujet : {Si tu leur demandes : « Qui a fait descendre du ciel une eau avec laquelle Il fit revivre la terre après sa mort ? », ils diront très certainement : « Allah ». Dis : « Louange à Allah ! » Mais la plupart d'entre eux ne raisonnent pas.} [Al-`Ankaboût : 63].",
                "Ils ne contestaient pas cela. Et, dans beaucoup de versets dans plusieurs sourates du Coran, [il est dit] qu'ils croient à l'unicité de seigneurie. Mais, ils ne croient pas en celle de divinité [d'adoration]."
            )
        ),
        Chapter(
            id = 7,
            title = "Le Comportement des Polythéistes",
            subtitle = "L'orgueil face à la vérité",
            paragraphs = listOf(
                "Allah dit, informant sur leur statut et réalité : {Quand on leur disait : « Point de divinité à part Allah », ils se gonflaient d'orgueil} [As-Sâffât : 35] ; et Il dit : {A-t-il réduit les divinités à un Seul Dieu ? Voilà une chose vraiment étonnante ». Et leurs notables partirent en disant : « Allez-vous en, et restez constants à vos dieux : c'est là vraiment une chose voulue.} [Sâd : 5], c'est-à-dire [que ce sont] des complots fomentés contre leurs divinités !",
                "Dans leurs adorations, ils prennent plusieurs divinités tout en reconnaissant qu'Allah Seularise par la création et la pourvoyance. Pourtant, l'adoration se trouve partagée, dans leur vision égarée.",
                "Le Prophète était venu, il les invita à cette unicité de divinité. Ils le démentirent (sur ce plan) ; et ils ne le démentaient pas au sujet de l'unicité de seigneurie, comme Allah dit : {Nous avons envoyé dans chaque communauté un Messager, [pour leur dire] : « Adorez Allah et écartez-vous du tâghoût ». Alors Allah en guida certains} [An-Nahl : 36], (…) en croyant en cette unicité et en suivant les messagers et leur obéissant ; {mais il y en eut qui ont été destinés à l'égarement.} en désavouant ce type d'unicité, ils succombèrent dans l'égarement lointain, ils mécrurent en Allah, Lui donnèrent un associé et démentirent les messagers.",
                "Ce qui est visé par-là, c'est que les communautés mécréantes – ayant démenti les messagers – ne reniaient pas l'unicité de seigneurie. Ils prêtaient foi à la seigneurie d'Allah, que c'est Lui qui avait créé cet univers ; qui le gère ; l'organise ; le créa ; leur octroya l'ouïe et la vue ; fit descendre, pour eux, la pluie ; fit pousser les plantes… Ils reconnaissaient tout cela et ne le reniaient pas."
            )
        ),
        Chapter(
            id = 8,
            title = "L'égarement du Kalâm et de la Philosophie",
            subtitle = "Réfutation des théologiens spéculatifs",
            paragraphs = listOf(
                "Beaucoup de groupes d'égarement se sont limités à cette unicité [de seigneurie] ! Ils ne savent pas autre chose. La parole « lâ ilâha illâ Allâh », ils la disent, la prononcent dans al-adhân sur les minarets, la déclarent cinq fois par jour ; mais ils ne savent pas quel est son sens ni ses conditions. Ceux qui les avaient égarés sur ce plan, ce sont les gens de philosophie et de logique, les gens du « kalâm » d'égarement dont l'imam Ash-Shâfi`î avait dit :",
                "« Mon verdict sur les gens du kalâm est qu'ils soient frappés par les branches de palmier et les souliers ; qu'on les fasse tourner dans les clans et les tribus et qu'on dise : « Ceci est le châtiment réservé à quiconque délaisse le livre d'Allah et la sounnah du Messager d'Allah et qui s'adonne au kalâm ». »",
                "Tous les imams de l'islam avaient interdit ce kalâm faux, auquel les sectes égarées se sont adonnées : depuis les khawârij, les rawâfid et les mou`tazilites jusqu'aux ach`arites et les soufis, qui les avaient rejoints. La science du kalâm les avait alors égarés, tandis que les prédécesseurs de cette communauté sont unanimes sur son interdiction, sur l'égarement de ses gens…",
                "Ash-Shâfi`î en avait dit : « Que le serviteur rencontre Allah par tout péché – en dehors de l’association – est meilleur que de Le rencontrer avec quelque chose de passion », – qu’Allah nous en préserve –.",
                "« Le kalâm » les avait jetés dans un égarement lointain. Il les conduisit dans le figement des noms d'Allah et de Ses attributs. Ils avaient évacué les attributs divins de leurs sens, en se basant sur leurs avis, raisons et philosophies. Ils ont corrompu le sens de l'unicité d'adoration. Ils ont assigné à cette parole « lâ ilâha illâ Allâh » des sens qui n'en font pas partie et qu'elle ne signifie pas.",
                "L'affaire qu'elle signifie est qu'Allah est La seule divinité digne d'adoration, que personne d'autre ne partage avec Lui. Le Coran montre que tous les prophètes avaient appelé au sens de cette parole et à sa réalisation. Allah dit : {Je n'ai créé les jinn et les hommes que pour qu'ils M'adorent.} [Adh-Dhâriyât : 56], c'est-à-dire pour qu'ils affirment « lâ ilâha illâ Allâh », qu'ils L'adorent et qu'ils se rapprochent de Lui par ce qu'Il mérite d'adoration qu'Il légiféra et permit."
            )
        ),
        Chapter(
            id = 9,
            title = "Le Sens Authentique de l'Unicité",
            subtitle = "Lâ ilâha illâ Allâh",
            paragraphs = listOf(
                "Nous devons donc connaître le sens de « lâ ilâha illâ Allâh » : qu'il n'y a pas de divinité à juste titre sauf Allah ; et qu'en dehors de Lui, tous les prophètes, les messagers, les anges et les pieux ne méritent pas un grain de l'adoration. En outre, ils sont tous des serviteurs d'Allah. Allah dit : {Tous ceux qui sont dans les cieux et sur la terre se rendront auprès du Tout Miséricordieux, [sans exception], en serviteurs. Il les a certes dénombrés et bien comptés. Et au Jour de la Résurrection, chacun d'eux se rendra seul auprès du Lui.} [Maryam : 93-95].",
                "Ils appellent tous à vouer l'adoration sincèrement et exclusivement à Allah. Ils sont les meilleurs adorateurs parmi les hommes. Les prophètes sont les maîtres et les guides des adorateurs et ils ont très peur d'Allah. Ils prient pour Lui, jeûnent pour Lui, s'acquittent de l'aumône pour Lui, ils s'humilient devant Lui, se soumettent à Lui, Le craignent, ils tremblent de peur d'Allah, ils sont pudiques envers Allah, ils placent leur confiance en Allah, ils s'appuient sur Lui dans toutes leurs affaires et situations, ils savent qu'il n'y a de changement ni de puissance si ce n'est de la part d'Allah et ils croient qu'ils ne détiennent ni mal, ni bien, ni mort, ni vie, ni ressuscitation pour eux-mêmes et ne possèdent rien de cela pour d'autres.",
                "Allah avait dit au meilleur, le plus noble et l'ultime parmi eux, qui est Mouhammad : {Dis : « Je ne détiens pour moi-même ni profit ni dommage, sauf ce qu'Allah veut. Et si je connaissais l'inconnaissable, j'aurais eu des biens en abondance et aucun mal ne m'aurait touché. Je ne suis qu'un avertisseur et un annonciateur pour des gens qui croient »} [Al-A`râf : 188]. Allah enjoint au Messager de dire cela, de le dire tout en y croyant et y appelant avec véracité, foi et dévouement.",
                "Il lui ordonne aussi de dire : {Dis: « Je ne possède aucun moyen pour vous faire du mal, ni pour vous mettre sur le chemin droit ».} [Al-Jinn : 21]. Si tel est l'état du Messager d'Allah, la meilleure créature d'Allah et la plus proche de Lui : qu'il ne détient ni profit ni méfait pour autrui, qu'en est-il donc d'autre que lui, qui lui est inférieur ?!"
            )
        ),
        Chapter(
            id = 10,
            title = "L'Exemple du Messager d'Allah",
            subtitle = "Le prêche à la Mecque",
            paragraphs = listOf(
                "Et quand Allah fit descendre sur lui : {Et avertis les gens qui te sont les plus proches.} [Ash-Shou`arâ' : 214], il monta sur [le mont] as-safâ et dit : « Ô fils d'untel et d'untel, ô fils de `Abd Al-Mouttalib, ô fils de Fihr, ô fils de Lou'ay ! » Ils se sont rassemblés.",
                "Il dit alors : « Je suis un avertisseur pour vous, avant un châtiment douloureux. Voyez-vous si je vous dis que des chevaux [des ennemis] se trouvent derrière ce mont, m'auriez-vous cru ? » Ils ont dit : « Nous ne t'avons jamais surpris en train de mentir ». Il dit : « Certes, je suis un avertisseur pour vous, avant un châtiment douloureux ».",
                "Aboû Lahab – qu'Allah l'enlaidisse – se mit alors en colère, l'insulta et dit : « Que la perte soit sur toi. Est-ce pour cela que tu nous réunis !? » Le Messager d'Allah les appela à l'adoration d'Allah seul et à l'abandon de l'adoration de Al-Llât, Al-`Ouzzâ et Manât la troisième autre, ainsi que les divinités similaires.",
                "Aboû Lahab était en tête des chefs opposés au Messager d'Allah et qui le démentaient. Il avait nui au Messager d'Allah d'une nuisance accrue, à cause de cette unicité. S'il leur demandait : « Qui est votre Seigneur ? Qui vous a créés ? » Ils auraient dit : « Allah ». « Qui créa le ciel ? La terre ?.. », ils disent : « Allah ». Ils ne le renient pas. Mais, quand il leur dit : « Nulle divinité n'est digne d'adoration en dehors d'Allah », ils s'enorgueillissaient."
            )
        ),
        Chapter(
            id = 11,
            title = "Les Trois Catégories d'Adoration",
            subtitle = "Cœur, Langue et Membres",
            paragraphs = listOf(
                "Nous devons savoir quel est le sens de « lâ ilâha illâ Allâh », de manière claire et limpide, et connaître le sens de l'adoration qu'elle contient. L'adoration comme dit shaykh al-islâm Ibn Taymiyya – qu'Allah lui fasse miséricorde – : « est un nom global qui renferme tout ce qu'Allah aime et agrée comme paroles et actes apparents et cachés ». Elle englobe l'adoration du cœur, de la langue et des membres :",
                "* Celle relative au cœur est la peur, l'espoir, le désir, la crainte, la confiance, l'amour et les choses similaires relevant des affaires du cœur. Ce sont là des adorations de [ce plan], qui sont indispensables et il n'est pas permis que nous en dirigions une partie à autre qu'Allah.",
                "** Celle de la langue : vient en tête de [cette catégorie] : la prononciation des deux attestations. Puis, les autres rappels, comme la récitation du Coran et les obligations et les surérogations. La prononciation par la langue est obligatoire en ce qui concerne la lecture d'Al-Fâtiha dans chaque unité de prière, comme le Prophète dit : « Il n'y a nulle prière à celui qui ne lit pas l'ouverture du Livre ». C’est là une affaire parmi celles que le musulman doit appliquer et dire. Une partie en est surrérogatoire, comme la récitation du Coran, la mention d'Allah, la glorification d'Allah (dire « soubhân Allâh »), prononcer l'attestation [lâ ilâha illâ Allâh] après la fin des prières, avant le sommeil, en voyage, en résidence, et les choses similaires. Les adorations se divisent en obligations et surérogations. Le musulman doit les connaître et se rapprocher d'Allah par leur biais.",
                "*** Celle des membres : vient en tête de [cette classe] le fait de se mettre debout dans la prière, l'agenouillement, la prosternation, les actes de pèlerinage et les autres cultes et adorations ; ainsi que les autres adorations des membres : tu pries pour Allah par tes membres, par ton cœur et par ta langue, tu t'agenouilles, te prosternes, te lèves de l'agenouillement, t'assois entre les deux prosternations ; et en pèlerinage : tu te déplaces par le corps afin d'accomplir ce pilier, tu tournes autour de l'édifice (la ka`ba), tu marches entre as-safâ et al-marwâ, tu accomplis les autres rites à Arafat et autre. Ce sont là des adorations réalisées par le corps, en sus du fait que tu as besoin d'argent pour les réaliser.",
                "Sur ces plans, les adorations sont nombreuses : celles du cœur, d'autres par la langue et d'autres encore par les membres. Tu dois les pratiquer avec sincérité ; [condition] indispensable dans toute adoration par laquelle nous adorons Allah.",
                "La compréhension de la croyance reçoit la dénomination de compréhension majeure ; celle des règles s'appelle la compréhension mineure, avec tous ses détails et elle repose sur la compréhension majeure qu'est la croyance. Nous implorons Allah pour qu'Il nous octroie – ainsi que vous – la bonne compréhension dans Sa religion.",
                "Et, que la prière et le salut en grand nombre soient sur notre Prophète Mouhammad, sur sa famille et ses compagnons."
            )
        )
    )
}
