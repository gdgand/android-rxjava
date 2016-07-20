package com.androidhuman.example.rxjavainaction.rx

import com.androidhuman.example.rxjavainaction.model.Content
import com.androidhuman.example.rxjavainaction.model.User
import rx.Observable

public inline fun Content.checksAvailability(): Observable<Void>
        = RxContent.checksAvailability(this)

public inline fun Content.checksBalance(user: User): Observable<Void>
        = RxContent.checksBalance(this, user)

public inline fun Content.checksContentRating(user: User): Observable<Void>
        = RxContent.checksContentRating(this, user)
