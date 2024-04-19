import useSWR from "swr";
import {getHost, isLoggedIn, setLoggedIn} from "@/app/utils";
import {ScopedMutator} from "swr/_internal";

class HttpError extends Error {
    body: any;
    status: number;

    constructor(body: any, status: number) {
        super(body?.message || 'An error occurred while fetching the data.');
        this.body = body;
        this.status = status;
    }
}

const fetcher = (url: string) => fetch(url, {
    credentials: 'include',
}).then(async (res) => {
    if (!res.ok) {
        throw new HttpError(await res.json(), res.status)
    }
    return res.json();
});

export type BoardDto = {
    board: Map<string, TileDto>
}

export type TileDto = {
    address: string,
    figure: FigureDto,
}

export type FigureDto = {
    color: 'WHITE' | 'BLACK',
    type: 'PAWN' | 'KNIGHT' | 'BISHOP' | 'ROOK' | 'QUEEN' | 'KING',
}

export function useBoard(): { board: BoardDto | undefined, isLoading: boolean, error: any } {
    const {data, error, isLoading} = useSWR(getHost() + '/chess/api/game/board', fetcher);
    return {
        board: data ? {board: new Map(Object.entries(data.board))} : undefined,
        isLoading,
        error
    };
}

export type LobbyDto = {
    openSessions: OpenSessionDto[],
}

export type OpenSessionDto = {
    id: number,
    opponent: PlayerDto,
    opponentSide: 'WHITE' | 'BLACK' | 'ANY',
}

export type PlayerDto = {
    login: string,
    name: string,
    bot: boolean,
}

export function useWaitingLobby(): { lobby?: LobbyDto } {
    let {data} = useSWR(getHost() + '/chess/api/game/open', fetcher);
    return {lobby: data};
}

export type UserDto = {
    login: string,
}

export function useUser(): { user: UserDto | undefined, isLoading: boolean } {
    const loggedIn = isLoggedIn();
    const {data, isLoading, error} = useSWR(getHost() + '/chess/api/user/me', (string) => {
        return loggedIn ? fetcher(string) : undefined;
    });
    if (error) setLoggedIn(false);
    return {user: data, isLoading};
}

export async function resetUser(mutate: ScopedMutator) {
    return mutate(getHost() + '/chess/api/user/me');
}

export function useDemoMatch(): { board: BoardDto | undefined } {
    const {data} = useSWR(getHost() + '/chess/api/game/board', fetcher);
    return {board: data ? {board: new Map(Object.entries(data.board))} : undefined};
}

export type LobbySide = "any" | "white" | "black";
export type LobbyOpponent = "bot" | "player";
export type SimpleSessionDto = {
    id: number,
    status: "IN_PROGRESS",
};

export async function createLobby(side: LobbySide, opponent: LobbyOpponent): Promise<SimpleSessionDto> {
    return fetch(getHost() + "/chess/api/game/start", {
        method: "POST",
        body: JSON.stringify({side: side.toUpperCase(), opponent: opponent.toUpperCase()}),
        headers: {"Content-Type": "application/json"},
        credentials: 'include',
    }).then(async function (response) {
        if (!response.ok) {
            const json = await response.json();
            throw "failed to create lobby: " + JSON.stringify(json, null, 2);
        }
        return response.json();
    })
}
