package com.jsevilla.memeschilenos.domain.usecase

import com.jsevilla.memeschilenos.domain.entity.Either
import com.jsevilla.memeschilenos.domain.entity.Failure
import com.jsevilla.memeschilenos.domain.entity.MemeEntity
import com.jsevilla.memeschilenos.domain.repository.MemesChileRepository

class GetListMemesChile(
    private val memesChileRepository: MemesChileRepository
) : BaseUseCase<MemeEntity, Any>() {
    override suspend fun run(params: Any): Either<Failure, MemeEntity> =
        memesChileRepository.getListMemes()
}
