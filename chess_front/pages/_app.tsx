import React from "react";
import type {Metadata} from 'next'
import {Inter} from 'next/font/google'
import 'bootstrap/dist/css/bootstrap.min.css';
import './globals.css'
import type {AppProps} from 'next/app'

export const metadata: Metadata = {
    title: 'overwave Dev Site',
    description: 'overwave.dev Landing Page',
}

const inter = Inter({subsets: ['latin']})

export default function App({Component, pageProps}: AppProps) {
    return (
            <main className={inter.className}>
                <Component {...pageProps} />
            </main>
    )
}