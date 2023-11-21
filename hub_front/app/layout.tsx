import React from "react";
import type {Metadata} from 'next'
import {Inter} from 'next/font/google'
import 'bootstrap/dist/css/bootstrap.min.css';
import './globals.css'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'overwave Dev Site',
  description: 'overwave.dev Landing Page',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en" className="h-100">
      <body className={`${inter.className} antialiased h-100`}>{children}</body>
    </html>
  )
}

