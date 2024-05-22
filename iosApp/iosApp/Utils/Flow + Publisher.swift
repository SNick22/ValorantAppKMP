//
//  Flow + Publisher.swift
//  iosApp
//
//  Created by Азат Абдрахманов on 14.05.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

func statePublisher<T>(_ watchable: CommonFlow<T>) -> AnyPublisher<T, Never> {
    return Deferred<Publishers.HandleEvents<PassthroughSubject<T, Never>>> {
        let subject = PassthroughSubject<T, Never>()
        let closeable = watchable.watch { next in
            guard let next else {
                return
            }
            subject.send(next)
        }

        return subject.handleEvents(receiveCancel: {
            closeable.close()
        })
    }.eraseToAnyPublisher()
}
