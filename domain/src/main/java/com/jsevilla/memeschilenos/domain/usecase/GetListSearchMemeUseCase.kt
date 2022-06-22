package com.jsevilla.memeschilenos.domain.usecase

import com.jsevilla.memeschilenos.domain.entity.Either
import com.jsevilla.memeschilenos.domain.entity.Failure
import com.jsevilla.memeschilenos.domain.entity.MemeEntity
import com.jsevilla.memeschilenos.domain.repository.MemesChileRepository

class GetListSearchMemeUseCase(
    private val memesChileRepository: MemesChileRepository
) : BaseUseCase<MemeEntity, GetListSearchMemeUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, MemeEntity> =
        memesChileRepository.getListSearchMemes(params.data)

    data class Params(val data: String)
}
