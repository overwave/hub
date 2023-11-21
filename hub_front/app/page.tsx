'use client'

import {useState} from 'react'
import Link from "next/link";
import Image from "next/image";

const navigation = [
    {name: 'About me', href: '/about'},
    {name: 'Contacts', href: '/contacts'},
    {name: 'Chess', href: '/chess'},
]

export default function Landing() {
    const [mobileMenuOpen, setMobileMenuOpen] = useState(false)

    return (
        <div className="px-4 pt-5 my-5 text-center border-bottom h-100">
            <h1 className="display-4 fw-bold">Hi, I’m overwave</h1>
            <div className="col-lg-6 mx-auto">
                <p className="lead mb-4">Quickly design and customize responsive mobile-first sites with Bootstrap, the
                    world’s most popular front-end open source toolkit, featuring Sass variables and mixins, responsive
                    grid system, extensive prebuilt components, and powerful JavaScript plugins.</p>
                <div className="d-grid gap-2 d-sm-flex justify-content-sm-center mb-5">
                    <Link href="chess" className="btn btn-primary btn-lg px-4 me-sm-3">Open Chess!</Link>
                    <button type="button" className="btn btn-outline-secondary btn-lg px-4">About me</button>
                </div>
            </div>
            <div className="overflow-hidden">
                <div className="container px-5">
                    <Image src="/rabbit.jpg" className="img-fluid border rounded-3 shadow-lg mb-5" alt="Example image"
                           width="700" height="700" loading="lazy"/>
                </div>
            </div>
        </div>
    )
}