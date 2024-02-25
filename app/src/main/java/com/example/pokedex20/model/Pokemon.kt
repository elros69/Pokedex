package com.example.pokedex20.model

import com.google.gson.annotations.SerializedName

// Objeto Pokemon
data class Pokemon (

    @SerializedName("abilities"                ) var abilities              : ArrayList<Abilities>   = arrayListOf(),
    //@SerializedName("base_experience"          ) var baseExperience         : Int?                   = null,
    //@SerializedName("cries"                    ) var cries                  : Cries?                 = Cries(),
    //@SerializedName("forms"                    ) var forms                  : ArrayList<Forms>       = arrayListOf(),
    //@SerializedName("game_indices"             ) var gameIndices            : ArrayList<GameIndices> = arrayListOf(),
    @SerializedName("height"                   ) var height                 : Int?                   = null,
    //@SerializedName("held_items"               ) var heldItems              : ArrayList<HeldItems>   = arrayListOf(),
    @SerializedName("id"                       ) var id                     : Int?                   = null,
    @SerializedName("is_default"               ) var isDefault              : Boolean?               = null,
    //@SerializedName("location_area_encounters" ) var locationAreaEncounters : String?                = null,
    //@SerializedName("moves"                    ) var moves                  : ArrayList<Moves>       = arrayListOf(),
    @SerializedName("name"                     ) var name                   : String?                = null,
    //@SerializedName("order"                    ) var order                  : Int?                   = null,
    //@SerializedName("past_abilities"           ) var pastAbilities          : ArrayList<String>      = arrayListOf(),
    //@SerializedName("past_types"               ) var pastTypes              : ArrayList<String>      = arrayListOf(),
    @SerializedName("species"                  ) var species                : Species?               = Species(),
    @SerializedName("sprites"                  ) var sprites                : Sprites?               = Sprites(),
    //@SerializedName("stats"                    ) var stats                  : ArrayList<Stats>       = arrayListOf(),
    @SerializedName("types"                    ) var types                  : ArrayList<Types>       = arrayListOf(),
    @SerializedName("weight"                   ) var weight                 : Int?                   = null,
    var favorito: Boolean = false

)

// Lista de habilidades

data class Abilities (

    @SerializedName("ability"   ) var ability  : Ability? = Ability(),
    @SerializedName("is_hidden" ) var isHidden : Boolean? = null,
    @SerializedName("slot"      ) var slot     : Int?     = null

)

// Habilidad del pokemon

data class Ability (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

data class Cries (

    @SerializedName("latest" ) var latest : String? = null,
    @SerializedName("legacy" ) var legacy : String? = null

)

data class Forms (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

data class Version (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

data class GameIndices (

    @SerializedName("game_index" ) var gameIndex : Int?     = null,
    @SerializedName("version"    ) var version   : Version? = Version()

)

data class Item (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

data class VersionDetails (

    @SerializedName("rarity"  ) var rarity  : Int?     = null,
    @SerializedName("version" ) var version : Version? = Version()

)


data class HeldItems (

    @SerializedName("item"            ) var item           : Item?                     = Item(),
    @SerializedName("version_details" ) var versionDetails : ArrayList<VersionDetails> = arrayListOf()

)

data class Move (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

data class MoveLearnMethod (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

data class VersionGroup (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

/*data class VersionGroupDetails (

    @SerializedName("level_learned_at"  ) var levelLearnedAt  : Int?             = null,
    @SerializedName("move_learn_method" ) var moveLearnMethod : MoveLearnMethod? = MoveLearnMethod(),
    @SerializedName("version_group"     ) var versionGroup    : VersionGroup?    = VersionGroup()

)*/


/*data class Moves (

    @SerializedName("move"                  ) var move                : Move?                          = Move(),
    @SerializedName("version_group_details" ) var versionGroupDetails : ArrayList<VersionGroupDetails> = arrayListOf()

)*/

data class Species (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

data class DreamWorld (

    @SerializedName("front_default" ) var frontDefault : String? = null,
    @SerializedName("front_female"  ) var frontFemale  : String? = null

)

data class Home (

    @SerializedName("front_default"      ) var frontDefault     : String? = null,
    @SerializedName("front_female"       ) var frontFemale      : String? = null,
    @SerializedName("front_shiny"        ) var frontShiny       : String? = null,
    @SerializedName("front_shiny_female" ) var frontShinyFemale : String? = null

)


data class OfficialArtwork (

@SerializedName("front_default" ) var frontDefault : String? = null,
@SerializedName("front_shiny"   ) var frontShiny   : String? = null

)

data class Showdown (

    @SerializedName("back_default"       ) var backDefault      : String? = null,
    @SerializedName("back_female"        ) var backFemale       : String? = null,
    @SerializedName("back_shiny"         ) var backShiny        : String? = null,
    @SerializedName("back_shiny_female"  ) var backShinyFemale  : String? = null,
    @SerializedName("front_default"      ) var frontDefault     : String? = null,
    @SerializedName("front_female"       ) var frontFemale      : String? = null,
    @SerializedName("front_shiny"        ) var frontShiny       : String? = null,
    @SerializedName("front_shiny_female" ) var frontShinyFemale : String? = null

)

data class Other (

    @SerializedName("dream_world"      ) var dreamWorld       : DreamWorld?       = DreamWorld(),
    @SerializedName("home"             ) var home             : Home?             = Home(),
    @SerializedName("official-artwork" ) var officialArtwork : OfficialArtwork? = OfficialArtwork(),
    @SerializedName("showdown"         ) var showdown         : Showdown?         = Showdown()

)

data class RedBlue (

@SerializedName("back_default"      ) var backDefault      : String? = null,
@SerializedName("back_gray"         ) var backGray         : String? = null,
@SerializedName("back_transparent"  ) var backTransparent  : String? = null,
@SerializedName("front_default"     ) var frontDefault     : String? = null,
@SerializedName("front_gray"        ) var frontGray        : String? = null,
@SerializedName("front_transparent" ) var frontTransparent : String? = null

)

data class GenerationI (

@SerializedName("red-blue" ) var redblue : RedBlue? = RedBlue(),
@SerializedName("yellow"   ) var yellow   : Yellow?   = Yellow()

)

data class Yellow (

    @SerializedName("back_default"      ) var backDefault      : String? = null,
    @SerializedName("back_gray"         ) var backGray         : String? = null,
    @SerializedName("back_transparent"  ) var backTransparent  : String? = null,
    @SerializedName("front_default"     ) var frontDefault     : String? = null,
    @SerializedName("front_gray"        ) var frontGray        : String? = null,
    @SerializedName("front_transparent" ) var frontTransparent : String? = null

)

data class Versions (

    @SerializedName("generation-i"    ) var generationI    : GenerationI?    = GenerationI(),

)

data class Sprites (

    @SerializedName("back_default"       ) var backDefault      : String?   = null,
    @SerializedName("back_female"        ) var backFemale       : String?   = null,
    @SerializedName("back_shiny"         ) var backShiny        : String?   = null,
    @SerializedName("back_shiny_female"  ) var backShinyFemale  : String?   = null,
    @SerializedName("front_default"      ) var frontDefault     : String?   = null,
    @SerializedName("front_female"       ) var frontFemale      : String?   = null,
    @SerializedName("front_shiny"        ) var frontShiny       : String?   = null,
    @SerializedName("front_shiny_female" ) var frontShinyFemale : String?   = null,
    @SerializedName("other"              ) var other            : Other?    = Other(),
    @SerializedName("versions"           ) var versions         : Versions? = Versions()

)

/*data class Stats (

    @SerializedName("base_stat" ) var baseStat : Int?  = null,
    @SerializedName("effort"    ) var effort   : Int?  = null,
    @SerializedName("stat"      ) var stat     : Stat? = Stat()

)*/

data class Stat (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

data class Types (

    @SerializedName("slot" ) var slot : Int?  = null,
    @SerializedName("type" ) var type : Type? = Type()

)

data class Type (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)